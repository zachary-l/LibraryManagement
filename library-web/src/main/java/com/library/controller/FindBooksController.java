package com.library.controller;

import com.library.dto.PageBean;
import com.library.service.FindBooksListService;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.JsonView;

@RequestMapping("/findBooks")
@Component("findBooksController")
@Scope
public class FindBooksController {
    @Inject("findBooksListService")
    private FindBooksListService findBooksListService;
    //罚金记录列表
    @RequestMapping("/list")
    public ViewResult findBooksList(int currentPage) {
        PageBean pageBean = findBooksListService.findBooksList(currentPage);
        return new JsonView(pageBean, "yyyy-MM-dd hh:mm:ss");
    }


}
