package com.library.controller;

import com.library.dao.exception.FlowException;
import com.library.dto.DataDto;
import com.library.dto.PageBean;
import com.library.model.InfoBook;
import com.library.model.TypeBooks;
import com.library.service.InfoBooksService;
import org.evergreen.web.HttpStatus;
import org.evergreen.web.ViewResult;
import org.evergreen.web.annotation.RequestMapping;
import org.evergreen.web.view.ForwardView;
import org.evergreen.web.view.Json;

@RequestMapping("/infoBooks")
public class InfoBooksController {
    /**
     * 查询全部
     */
    @RequestMapping("/findBooks")
    public ViewResult findBook(int currentPage) {
        return new Json(new InfoBooksService().findInfoBooks(currentPage), "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 根据图书类型查询
     */
    @RequestMapping("/findBooksById")
    public ViewResult findBooksById(TypeBooks typeBooks, int currentPage) {
        if (typeBooks.getTyId() == 0) {
            return new ForwardView("addBooks");
        } else {
            return new Json(new InfoBooksService().findInfoBooksById(typeBooks, currentPage), "yyyy-MM-dd hh:mm:ss");
        }
    }

    /**
     * 添加书籍
     */
    @RequestMapping("/addBooks")
    public ViewResult addBook(InfoBook book, int currentPage) {
        DataDto data = new DataDto();
        InfoBooksService service = new InfoBooksService();
        try {
            PageBean pageBean = service.addInfoBook(book, currentPage);
            data.setStatusCode(HttpStatus.SC_OK);
            data.setValue(pageBean);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data, "yyyy-MM-dd hh:mm:ss");
    }
}
