package com.library.dao;

import com.library.utils.DBUtil;
import org.evergreen.db.helper.ResultSetHandler;
import org.evergreen.db.helper.SQLExecutor;
import org.evergreen.db.helper.handler.ColumnHandler;
import org.evergreen.db.helper.handler.MapListHandler;
import org.framework.beans.annotation.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Component("findListDao")
public class FindListDao {
    /**
     * 违惩记录，分页
     *
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<Map<String, Object>> findList(int firstResult, int maxResult) {
        List<Map<String, Object>> list = null;
        String sql = "SELECT * FROM REGISTER_FINE R_F JOIN TYPE_FIND T_F ON R_F.TF_ID = T_F.TF_ID " +
                "JOIN (SELECT R_B.RET_ID,R_B.RET_TIME,R_B.RET_NUM,AA.BOR_TIME,AA.BOR_ID,AA.RE_ID,AA.RE_NAME FROM RETURN_BOOKS R_B " +
                "JOIN (SELECT B.BOR_TIME,B.BOR_ID,R.RE_ID,R.RE_NAME FROM BORROW_BOOKS B " +
                "JOIN READER_USERS R ON R.RE_ID = B.RE_ID) AA ON R_B.BOR_ID = AA.BOR_ID) INF1 ON R_F.BOR_ID = INF1.BOR_ID ORDER BY RF_ID DESC LIMIT ?,?;";
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
        String sql = "SELECT COUNT(*) FROM REGISTER_FINE";

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
     * 删除罚金记录
     * @param rfId
     * @return
     */
    public int deleteFindList(int rfId){
        int row = 0;
        String sql = "DELETE FROM REGISTER_FINE WHERE RF_ID=?";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        try {
            row = se.executeUpdate(sql,rfId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }


}
