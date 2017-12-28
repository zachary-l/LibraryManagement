package com.library.controller;

import com.library.service.TypeBooksService;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.JsonView;

@RequestMapping("/typeBook")
@Component("typeBookController")
@Scope
public class TypeBookController {
    @Inject("typeBooksService")
    private TypeBooksService typeBooksService;
    @RequestMapping("/findType")
    public ViewResult findTypeBooks() {
        return new JsonView(typeBooksService.findTypeBooks());
    }
}
