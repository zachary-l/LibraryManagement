package com.library.service;

import com.library.dao.FindListDao;
import com.library.dto.PageBean;
import com.library.exception.FlowException;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;

import java.util.List;
import java.util.Map;
@Component("findBooksListService")
public class FindListService {
    @Inject("findListDao")
    private FindListDao findListDao;
    //显示列表
    public PageBean findBooksList(int currentPage) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(8);
        int countResult = findListDao.count();
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = findListDao.findList(pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }
    //删除罚金记录
    public PageBean deleteFindList(int rfId,int currentPage){
        int row = 0;
        row = findListDao.deleteFindList(rfId);
        if(row==0){
            throw new FlowException("删除罚金记录失败");
        }
        return findBooksList(currentPage);
    }
}
