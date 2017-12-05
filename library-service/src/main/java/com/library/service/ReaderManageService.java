package com.library.service;

import com.library.dao.ReaderDao;
import com.library.dto.PageBean;
import com.library.exception.FlowException;
import com.library.model.Reader;

import java.util.List;
import java.util.Map;

public class ReaderManageService {
    public PageBean find(int currentPage){
        ReaderDao dao = new ReaderDao();
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = dao.count();
        //设置中记录数,会自动计算出总页数
        pageBean.setCountResult(countResult);
        List<Map<String,Object>> list = dao.findReader(pageBean.getFirstResult(),pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 添加读者
     * @param re
     * @param currentPage
     * @return
     */
    public PageBean addReader(Reader re,int currentPage){
        ReaderDao dao = new ReaderDao();
        int row = dao.addReader(re);
        if(row==0){
            throw new FlowException("添加读者失败",401);
        }else {
            return find(currentPage);
        }
    }
    /**
     * 删除读者
     */
    public PageBean deleteReader(int id,int currentPage){
        int row = new ReaderDao().deleteReader(id);
        if(row==0){
            throw new FlowException("添加读者失败",401);
        }else {
            return find(currentPage);
        }

    }
}
