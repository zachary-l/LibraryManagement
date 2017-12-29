package com.library.controller;

import com.library.exception.FlowException;
import com.library.dto.DataDto;
import com.library.dto.PageBean;
import com.library.model.Admin;
import com.library.model.BorrowBooks;
import com.library.service.BorrowBooksService;
import com.library.utils.ChangeDateUtil;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.mvc.ActionContext;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.JsonView;

import java.util.Date;

@RequestMapping("/borrow")
@Component("borrowBooksController")
@Scope
public class BorrowBooksController {
    @Inject("borrowBooksService")
    private BorrowBooksService borrowBooksService;
    //显示借阅记录列表
    @RequestMapping("/findList")
    public ViewResult findBorrowList(int currentPage) {
        PageBean pageBean = borrowBooksService.findBorrowList(currentPage);
        return new JsonView(pageBean, "yyyy-MM-dd hh:mm:ss");
    }
    //借阅书籍
    @RequestMapping("/addBooks")
    public ViewResult addBorrowBooks(BorrowBooks borrowBooks,String startTime,String lastTime, int currentPage) {

        //(因为复杂类型里面的复杂类型尚未进行处理，所以单个获取)
       borrowBooks.setBorTime(ChangeDateUtil.StringToDate(startTime));
        borrowBooks.setBorTimeLast(ChangeDateUtil.StringToDate(lastTime));
        Admin admin = (Admin) ActionContext.getContext().getSessionMap().get("admin");
        borrowBooks.setAdId(admin.getAdId());
        System.out.println(borrowBooks);
        DataDto data = new DataDto();
        try {
            PageBean pageBean = borrowBooksService.borrowBooks(currentPage, borrowBooks);
            data.setStatusCode(200);
            data.setValue(pageBean);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data, "yyyy-MM-dd hh:mm:ss");

    }

}
