package com.library.controller;

import com.library.dto.DataDto;
import com.library.dto.PageBean;
import com.library.exception.FlowException;
import com.library.model.Admin;
import com.library.model.BorrowBooks;
import com.library.service.BorrowBooksService;
import org.evergreen.web.ActionContext;
import org.evergreen.web.HttpStatus;
import org.evergreen.web.ViewResult;
import org.evergreen.web.annotation.RequestMapping;
import org.evergreen.web.view.Json;

import java.sql.SQLException;

@RequestMapping("/borrow")
public class BorrowBooksAction {
    //借阅记录列表
    @RequestMapping("/findList")
    public ViewResult findBorrowList(int currentPage) {
        return new Json(new BorrowBooksService().findBorrowList(currentPage), "yyyy-MM-dd hh:mm:ss");
    }

    //借阅书籍
    @RequestMapping("/add")
    public ViewResult addBorrowBooks(BorrowBooks borrowBooks, int currentPage) {
        Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
        borrowBooks.setAdId(admin.getAdId());
        BorrowBooksService service = new BorrowBooksService();
        DataDto data = new DataDto();
        try {
            PageBean pageBean = service.borrowBooks(currentPage, borrowBooks);
            data.setStatusCode(HttpStatus.SC_OK);
            data.setValue(pageBean);
        } catch (FlowException e) {
            e.printStackTrace();
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data,"yyyy-MM-dd hh:mm:ss");
    }

}
