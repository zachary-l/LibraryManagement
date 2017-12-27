package com.library.service;

import com.library.dao.TypeBookDao;
import com.library.model.TypeBooks;

import java.util.List;

public class TypeBooksService {
    public List<TypeBooks> findTypeBooks() {
        TypeBookDao dao = new TypeBookDao();
        List<TypeBooks> list = dao.findTypeBooks();
        return list;
    }
}
