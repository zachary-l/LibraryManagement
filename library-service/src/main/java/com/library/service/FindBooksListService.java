package com.library.service;

import com.library.dao.FindListDao;
import com.library.dto.PageBean;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;

import java.util.List;
import java.util.Map;
@Component("findBooksListService")
public class FindBooksListService {
    @Inject("findListDao")
    private FindListDao findListDao;
    public PageBean findBooksList(int currentPage) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = findListDao.count();
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = findListDao.findList(pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }
}
