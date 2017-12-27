package com.library.controller;

import com.library.dto.PageBean;
import com.library.service.FindBooksListService;
import org.evergreen.web.ViewResult;
import org.evergreen.web.annotation.RequestMapping;
import org.evergreen.web.view.Json;

@RequestMapping("/findBooks")
public class FindBooksController {
    //罚金记录列表
    @RequestMapping("/list")
    public ViewResult findBooksList(int currentPage) {
        FindBooksListService service = new FindBooksListService();
        PageBean pageBean = service.findBooksList(currentPage);
        return new Json(pageBean, "yyyy-MM-dd hh:mm:ss");
    }


}
