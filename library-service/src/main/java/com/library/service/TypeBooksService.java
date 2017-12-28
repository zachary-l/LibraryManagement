package com.library.service;

import com.library.dao.TypeBookDao;
import com.library.model.TypeBooks;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;

import java.util.List;
@Component("typeBooksService")
public class TypeBooksService {
    @Inject("typeBookDao")
    private TypeBookDao typeBookDao;
    public List<TypeBooks> findTypeBooks() {
        List<TypeBooks> list = typeBookDao.findTypeBooks();
        return list;
    }
}
