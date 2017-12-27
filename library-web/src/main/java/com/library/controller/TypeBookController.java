package com.library.controller;

import com.library.service.TypeBooksService;
import org.evergreen.web.ViewResult;
import org.evergreen.web.annotation.RequestMapping;
import org.evergreen.web.view.Json;

@RequestMapping("/typeBook")
public class TypeBookController {
    @RequestMapping("/findType")
    public ViewResult findTypeBooks() {
        return new Json(new TypeBooksService().findTypeBooks());
    }
}
