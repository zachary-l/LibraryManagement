package com.library.dao;

import com.library.dao.exception.FlowException;
import com.library.model.BorrowBooks;
import com.library.model.ReturnBooks;
import com.library.utils.DBUtil;
import com.library.utils.SQLExecutorFactory;
import org.evergreen.db.helper.ResultSetHandler;
import org.evergreen.db.helper.SQLExecutor;
import org.evergreen.db.helper.handler.ColumnHandler;
import org.evergreen.db.helper.handler.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 图书借阅管理
 */
public class BorrowBooksDao {
    public List<Map<String, Object>> findBorrow(int firstResult, int maxResult) {
        List<Map<String, Object>> list = null;
        String sql = "SELECT * FROM BORROW_BOOKS B JOIN INFORMATION_BOOKS T ON B.IN_ID = T.IN_ID JOIN READER_USERS R ON R.RE_ID = B.RE_ID " +
                " JOIN ADMIN_USERS A ON A.AD_ID = B.AD_ID WHERE B.BOR_RETURN = 1 ORDER BY BOR_ID DESC LIMIT ?,?;";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        MapListHandler handler = new MapListHandler();
        try {
            list = se.executeQuery(sql, handler, firstResult, maxResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询总页数
     *
     * @return
     */
    public int count() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM BORROW_BOOKS";

        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        ResultSetHandler<Integer> handler = new ColumnHandler<>(1, Integer.class);
        try {
            count = se.executeQuery(sql, handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 借书
     */
    public int borrowBooks(BorrowBooks b) {
        int row = 0;
        String sql = "INSERT INTO BORROW_BOOKS(BOR_TIME,BOR_TIME_LAST,BOR_NUM,BOR_RETURN,IN_ID,RE_ID,AD_ID)VALUES(?,?,?,?,?,?,?);";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        try {
            row = se.executeUpdate(sql, b.getBorTime(), b.getBorTimeLast(), b.getBorNum(), b.getBorReturn(), b.getInId(), b.getReId(), b.getAdId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 归还书籍
     */
    public int returnBooks(ReturnBooks r) {
        int row = 0;
        String sql = "UPDATE BORROW_BOOKS SET BOR_RETURN = 0 WHERE BOR_ID =?;";
        SQLExecutor se = SQLExecutorFactory.getCurrentSQLExecutor();
        try {
            row = se.executeUpdate(sql, r.getBorId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FlowException("归还书籍失败", 401);
        }
        return row;
    }

    /**
     * 添加归还记录
     */
    public int addReturnBooks(ReturnBooks rb) {
        int row = 0;
        String sql = "INSERT INTO RETURN_BOOKS(RET_TIME,RET_NUM,BOR_ID)VALUES(?,?,?);";
        SQLExecutor se = SQLExecutorFactory.getCurrentSQLExecutor();
        try {
            row = se.executeUpdate(sql, rb.getRetTime(), rb.getRetNum(), rb.getBorId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FlowException("添加归还记录失败", 401);
        }
        return row;
    }


}
