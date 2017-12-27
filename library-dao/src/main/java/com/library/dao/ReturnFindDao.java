package com.library.dao;

import com.library.dao.exception.FlowException;
import com.library.model.RetisgerFind;
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

public class ReturnFindDao {

    /**
     * 添加违惩记录
     */
    public int addBorrowFind(RetisgerFind re, ReturnBooks r) {
        int row = 0;
        String sql = "INSERT INTO REGISTER_FINE(RF_EXPLAIN,RF_MONEY,TF_ID,BOR_ID)VALUES(?,?,?,?);";
        SQLExecutor se = SQLExecutorFactory.getCurrentSQLExecutor();
        try {
            row = se.executeUpdate(sql, re.getRfExplain(), re.getRfMoney(), re.getTypeFind(), r.getBorId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FlowException("添加惩罚记录失败", 401);

        }
        return row;
    }

    /**
     * 查询全部归还书籍记录，分页
     *
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<Map<String, Object>> findReturnBooks(int firstResult, int maxResult) {
        List<Map<String, Object>> list = null;
        String sql = "SELECT * FROM RETURN_BOOKS R_B LEFT JOIN (SELECT B.BOR_TIME,B.BOR_ID,T.IN_ID,T.IN_NAME,R.RE_ID,R.RE_NAME,R.RE_CREDIT,A.AD_NAME FROM BORROW_BOOKS B " +
                "JOIN INFORMATION_BOOKS T ON B.IN_ID = T.IN_ID JOIN READER_USERS R ON R.RE_ID = B.RE_ID " +
                "JOIN ADMIN_USERS A ON A.AD_ID = B.AD_ID) AA ON R_B.BOR_ID = AA.BOR_ID ORDER BY RET_ID DESC LIMIT ?,?;";
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
        String sql = "SELECT COUNT(*) FROM RETURN_BOOKS";

        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        ResultSetHandler<Integer> handler = new ColumnHandler<>(1, Integer.class);
        try {
            count = se.executeQuery(sql, handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
