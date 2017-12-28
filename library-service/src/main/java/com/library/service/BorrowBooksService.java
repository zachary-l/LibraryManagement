package com.library.service;

import com.library.dao.BorrowBooksDao;
import com.library.dao.ReturnFindDao;
import com.library.exception.FlowException;
import com.library.dto.PageBean;
import com.library.model.BorrowBooks;
import com.library.model.RetisgerFind;
import com.library.model.ReturnBooks;
import com.library.utils.SQLExecutorFactory;
import org.evergreen.db.helper.SQLExecutor;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;

import java.util.List;
import java.util.Map;
@Component("borrowBooksService")
public class BorrowBooksService {
    @Inject("borrowBooksDao")
    private BorrowBooksDao borrowBooksDao;
    /**
     * 查看借阅记录列表
     */
    public PageBean findBorrowList(int currentPage) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setMaxResult(10);
        int countResult = borrowBooksDao.count();
        pageBean.setCountResult(countResult);
        List<Map<String, Object>> list = borrowBooksDao.findBorrow(pageBean.getFirstResult(), pageBean.getMaxResult());
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * e借阅书籍
     */
    public PageBean borrowBooks(int currentPage, BorrowBooks borrowBooks) {
        int row = borrowBooksDao.borrowBooks(borrowBooks);
        if (row == 0) {
            throw new FlowException("借阅失败");
        } else {
            return findBorrowList(currentPage);
        }

    }

    /**
     * 归还书籍删除借阅数据并添加一行归还记录
     */
    public PageBean returnBooks(ReturnBooks r, int currentPage, int whetherFind, RetisgerFind re) {
        System.out.println(whetherFind);
        SQLExecutor se = SQLExecutorFactory.getCurrentSQLExecutor();
        ReturnFindDao findDao = new ReturnFindDao();
        try {
            //开始事务
            se.beginTranstaction();
            if (whetherFind == 0) {
                //dao包throw一个异常
                borrowBooksDao.returnBooks(r);
                borrowBooksDao.addReturnBooks(r);
            } else if (whetherFind == 1) {
                //re说明，金额，类型，r借阅记录外键
                findDao.addBorrowFind(re, r);
                borrowBooksDao.returnBooks(r);
                borrowBooksDao.addReturnBooks(r);
            }
            //提交事务
            se.commit();
        } catch (FlowException e) {
            //回滚事务
            se.rollback();
            throw new FlowException("归还失败");
        } finally {
            SQLExecutorFactory.removeLocal();
        }
        return findBorrowList(currentPage);
    }

}
