package com.library.dao;

import com.library.model.InfoBook;
import com.library.model.TypeBooks;
import com.library.utils.DBUtil;
import org.evergreen.db.helper.ResultSetHandler;
import org.evergreen.db.helper.SQLExecutor;
import org.evergreen.db.helper.handler.ColumnHandler;
import org.evergreen.db.helper.handler.MapListHandler;
import org.framework.beans.annotation.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Component("infoBookDao")
public class InfoBookDao {
    /**
     * 查询全部书籍
     */
    public List<Map<String, Object>> findBooksList(int firstResult, int maxResult) {
        String sql = "SELECT * FROM INFORMATION_BOOKS I JOIN TYPE_BOOKS T ON I.TY_ID=T.TY_ID ORDER BY IN_ID DESC LIMIT ?,?;";
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
     * 根据书籍类型ID查询书籍
     */
    public List<Map<String, Object>> findBooksListById(TypeBooks typeBooks, int firstResult, int maxResult) {
        String sql = "SELECT * FROM INFORMATION_BOOKS I JOIN TYPE_BOOKS T ON I.TY_ID=T.TY_ID WHERE I.TY_ID=? ORDER BY IN_ID DESC LIMIT ?,?;";
        List<Map<String, Object>> list = null;
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        MapListHandler handler = new MapListHandler();
        try {
            list = se.executeQuery(sql, handler, typeBooks.getTyId(), firstResult, maxResult);
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
        String sql = "SELECT COUNT(*) FROM INFORMATION_BOOKS";

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
     * 查询总页数
     *
     * @return
     */
    public int count2(TypeBooks typeBooks) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM INFORMATION_BOOKS WHERE TY_ID = ?";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        ResultSetHandler<Integer> handler = new ColumnHandler<>(1, Integer.class);
        try {
            count = se.executeQuery(sql, handler, typeBooks.getTyId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 添加书籍
     */
    public int addInfoBooks(InfoBook book) {
        int row = 0;
        String sql = "INSERT INTO INFORMATION_BOOKS(IN_NAME,IN_AUTHOR,IN_PUBLICATIONTIME,IN_UPLIBRARYTIME,IN_UPDATETIME,IN_PRESS,IN_PRICE,IN_NUM,IN_BORROWFROM,TY_ID)" +
                "VALUES(？，？，？，？，？，？，？，？，？，？);";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        try {
            row = se.executeUpdate(sql, book.getInName(), book.getInAuthor(), book.getInPubliCationTime(), book.getInUplibraryTime(), book.getInUpdateTime(), book.getInPress(), book.getInPrice(), book.getInNum(), book.getInBorrowFrom(), book.getInId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    /**
     * 删除书籍书籍
     */
    public int upInfoBooks(int inId){
        int row = 0;
        String sql = "DELETE  FROM INFORMATION_BOOKS WHERE IN_ID = ?";
        SQLExecutor se  = new SQLExecutor(DBUtil.getConnection());
        try {
            se.executeUpdate(sql,inId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }


}
