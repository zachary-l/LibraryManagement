package com.library.service;

import com.library.dao.InfoBookDao;
import com.library.exception.FlowException;
import com.library.dto.PageBean;
import com.library.model.InfoBook;
import com.library.model.TypeBooks;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;

import java.util.List;
import java.util.Map;
@Component("infoBooksService")
public class InfoBooksService {
    @Inject("infoBookDao")
    private InfoBookDao infoBookDao;
    /**
     * 分页查询
     */
    public PageBean findInfoBooks(int currentPage) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = infoBookDao.count();
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = infoBookDao.findBooksList(pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;

    }

    /**
     * 根据图书类型查询
     */
    public PageBean findInfoBooksById(TypeBooks typeBooks, int currentPage) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = infoBookDao.count2(typeBooks);
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = infoBookDao.findBooksListById(typeBooks, pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 添加书籍
     */
    public PageBean addInfoBook(InfoBook book, int currentPage) {
        int row = 0;
        row = infoBookDao.addInfoBooks(book);
        if (row == 0) {
            throw new FlowException("添加书籍失败");
        } else {
            return findInfoBooks(currentPage);
        }
    }
}
