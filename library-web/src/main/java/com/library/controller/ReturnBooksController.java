package com.library.controller;

import com.library.dao.exception.FlowException;
import com.library.dto.DataDto;
import com.library.dto.PageBean;
import com.library.model.RetisgerFind;
import com.library.model.ReturnBooks;
import com.library.service.BorrowBooksService;
import com.library.service.ReturnBooksListService;
import org.evergreen.web.HttpStatus;
import org.evergreen.web.ViewResult;
import org.evergreen.web.annotation.RequestMapping;
import org.evergreen.web.view.Json;

import java.util.Date;

@RequestMapping("/returnBooks")
public class ReturnBooksController {

    //归还书籍列表
    @RequestMapping("/findList")
    public ViewResult resultBooksList(int currentPage) {
        ReturnBooksListService service = new ReturnBooksListService();
        PageBean pageBean = service.findReturnBooksList(currentPage);
        return new Json(pageBean, "yyyy-MM-dd hh:mm:ss");
    }


    //归还书籍
    @RequestMapping("/addSincerity")
    public ViewResult addSinReturnBooks(ReturnBooks r, int currentPage, int whetherFind, RetisgerFind re) {
        r.setRetNum(1);
        r.setRetTime(new Date());
        DataDto data = new DataDto();
        BorrowBooksService service = new BorrowBooksService();
        try {
            PageBean pageBean = service.returnBooks(r, currentPage, whetherFind, re);
            data.setValue(pageBean);
            data.setStatusCode(HttpStatus.SC_OK);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data, "yyyy-MM-dd hh:mm:ss");
    }

}
