package com.library.dao;

import com.library.model.Reader;
import com.library.utils.DBUtil;
import org.evergreen.db.helper.ResultSetHandler;
import org.evergreen.db.helper.SQLExecutor;
import org.evergreen.db.helper.handler.ColumnHandler;
import org.evergreen.db.helper.handler.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReaderDao {

    /**
     * 查询全部用户分页
     *
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<Map<String, Object>> findReader(int firstResult, int maxResult) {
        String sql = "SELECT * FROM READER_USERS ORDER BY RE_ID DESC LIMIT ?,?;";
        List<Map<String, Object>> list = null;
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        MapListHandler handler = new MapListHandler();
        try {
            list = se.executeQuery(sql, handler, firstResult, maxResult);
        } catch (Exception e) {
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
        String sql = "SELECT COUNT(*) FROM READER_USERS";

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
     * 添加读者
     *
     * @param re
     * @return
     */
    public int addReader(Reader re) {
        int row = 0;
        String sql = "INSERT INTO READER_USERS(RE_NAME,RE_SEX,RE_PHONE,RE_ADDRESS,RE_CREDIT) VALUES(?,?,?,?,?);";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());

        try {
            row = se.executeUpdate(sql, re.getReName(), re.getSex(), re.getPhone(), re.getReAddress(), re.getReCredit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public int deleteReader(int id) {
        int row = 0;
        String sql = "DELETE FROM READER_USERS WHERE RE_ID = ?";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        try {
            row = se.executeUpdate(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}