package com.library.controller;

import com.library.exception.FlowException;
import com.library.dto.DataDto;
import com.library.dto.PageBean;
import com.library.model.RetisgerFind;
import com.library.model.ReturnBooks;
import com.library.service.BorrowBooksService;
import com.library.service.ReturnBooksListService;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.JsonView;

import java.util.Date;

@RequestMapping("/returnBooks")
@Component("returnBooksController")
@Scope
public class ReturnBooksController {
    @Inject("returnBooksListService")
    private ReturnBooksListService returnBooksListService;
    @Inject("borrowBooksService")
    private BorrowBooksService borrowBooksService;
    //归还书籍列表
    @RequestMapping("/findList")
    public ViewResult resultBooksList(int currentPage) {
        PageBean pageBean = returnBooksListService.findReturnBooksList(currentPage);
        return new JsonView(pageBean, "yyyy-MM-dd hh:mm:ss");
    }


    //归还书籍
    @RequestMapping("/addSincerity")
    public ViewResult addSinReturnBooks(ReturnBooks r, int currentPage, int whetherFind, RetisgerFind re) {
        r.setRetNum(1);
        r.setRetTime(new Date());
        DataDto data = new DataDto();
        try {
            PageBean pageBean = borrowBooksService.returnBooks(r, currentPage, whetherFind, re);
            data.setValue(pageBean);
            data.setStatusCode(200);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(200);
        }
        return new JsonView(data, "yyyy-MM-dd hh:mm:ss");
    }

}
