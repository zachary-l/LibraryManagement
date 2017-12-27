package com.library.service;

import com.library.dao.ReturnFindDao;
import com.library.dto.PageBean;

import java.util.List;
import java.util.Map;

public class ReturnBooksListService {
    //查看归还记录列表，分页
    public PageBean findReturnBooksList(int currentPage) {
        PageBean pageBean = new PageBean();
        ReturnFindDao dao = new ReturnFindDao();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = dao.count();
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = dao.findReturnBooks(pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }
}
