package com.library.service;

import com.library.dao.BorrowBooksDao;
import com.library.dao.ReturnFindDao;
import com.library.dao.exception.FlowException;
import com.library.dto.PageBean;
import com.library.model.BorrowBooks;
import com.library.model.RetisgerFind;
import com.library.model.ReturnBooks;
import com.library.utils.SQLExecutorFactory;
import org.evergreen.db.helper.SQLExecutor;

import java.util.List;
import java.util.Map;

public class BorrowBooksService {
    /**
     * 查看借阅记录列表
     */
    public PageBean findBorrowList(int currentPage) {
        PageBean pageBean = new PageBean();
        BorrowBooksDao dao = new BorrowBooksDao();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = dao.count();
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = dao.findBorrow(pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * e借阅书籍
     */
    public PageBean borrowBooks(int currentPage, BorrowBooks borrowBooks) {
        BorrowBooksDao dao = new BorrowBooksDao();
        int row = dao.borrowBooks(borrowBooks);
        if (row == 0) {
            throw new FlowException("借阅失败", 401);
        } else {
            return new BorrowBooksService().findBorrowList(currentPage);
        }

    }

    /**
     * 归还书籍删除借阅数据并添加一行归还记录
     */
    public PageBean returnBooks(ReturnBooks r, int currentPage, int whetherFind, RetisgerFind re) {
        System.out.println(whetherFind);
        SQLExecutor se = SQLExecutorFactory.getCurrentSQLExecutor();
        BorrowBooksDao dao = new BorrowBooksDao();
        ReturnFindDao findDao = new ReturnFindDao();
        try {
            //开始事务
            se.beginTranstaction();
            if (whetherFind == 0) {
                //dao包throw一个异常
                dao.returnBooks(r);
                dao.addReturnBooks(r);
            } else if (whetherFind == 1) {
                //re说明，金额，类型，r借阅记录外键
                findDao.addBorrowFind(re, r);
                dao.returnBooks(r);
                dao.addReturnBooks(r);
            }
            //提交事务
            se.commit();
        } catch (FlowException e) {
            //回滚事务
            se.rollback();
            throw new FlowException("归还失败", 401);
        } finally {
            SQLExecutorFactory.removeLocal();
        }
        return new BorrowBooksService().findBorrowList(currentPage);
    }

}
