package com.library.controller;

import com.library.model.TypeBooks;
import com.library.service.TypeBooksService;
import org.evergreen.web.ViewResult;
import org.evergreen.web.annotation.RequestMapping;
import org.evergreen.web.view.Json;

import java.util.List;

@RequestMapping("/typeBook")
public class TypeBookAction {
    @RequestMapping("/findType")
    public ViewResult findTypeBooks(){
        return new Json(new TypeBooksService().findTypeBooks());
    }
}
