package com.library.service;

import com.library.dao.ReturnFindDao;
import com.library.dto.PageBean;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;

import java.util.List;
import java.util.Map;
@Component("returnBooksListService")
public class ReturnBooksListService {
    @Inject("returnFindDao")
    private ReturnFindDao returnFindDao;
    //查看归还记录列表，分页
    public PageBean findReturnBooksList(int currentPage) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(8);
        int countResult = returnFindDao.count();
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = returnFindDao.findReturnBooks(pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }
}
