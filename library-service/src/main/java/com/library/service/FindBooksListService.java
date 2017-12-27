package com.library.service;

import com.library.dao.FindListDao;
import com.library.dto.PageBean;

import java.util.List;
import java.util.Map;

public class FindBooksListService {
    public PageBean findBooksList(int currentPage) {
        PageBean pageBean = new PageBean();
        FindListDao dao = new FindListDao();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = dao.count();
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = dao.findList(pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }
}
