package com.library.service;

import com.library.dao.InfoBookDao;
import com.library.dao.exception.FlowException;
import com.library.dto.PageBean;
import com.library.model.InfoBook;
import com.library.model.TypeBooks;

import java.util.List;
import java.util.Map;

public class InfoBooksService {
    /**
     * 分页查询
     */
    public PageBean findInfoBooks(int currentPage) {
        PageBean pageBean = new PageBean();
        InfoBookDao dao = new InfoBookDao();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = dao.count();
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = dao.findBooksList(pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;

    }

    /**
     * 根据图书类型查询
     */
    public PageBean findInfoBooksById(TypeBooks typeBooks, int currentPage) {
        PageBean pageBean = new PageBean();
        InfoBookDao dao = new InfoBookDao();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = dao.count2(typeBooks);
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = dao.findBooksListById(typeBooks, pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 添加书籍
     */
    public PageBean addInfoBook(InfoBook book, int currentPage) {
        int row = 0;
        InfoBookDao dao = new InfoBookDao();
        row = dao.addInfoBooks(book);
        if (row == 0) {
            throw new FlowException("添加书籍失败", 401);
        } else {
            return findInfoBooks(currentPage);
        }
    }
}
