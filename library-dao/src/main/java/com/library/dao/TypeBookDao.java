package com.library.dao;

import com.library.model.TypeBooks;
import com.library.utils.DBUtil;
import org.evergreen.db.helper.ResultSetHandler;
import org.evergreen.db.helper.SQLExecutor;
import org.evergreen.db.helper.handler.BeanListHandler;
import org.framework.beans.annotation.Component;

import java.sql.SQLException;
import java.util.List;
@Component("typeBookDao")
public class TypeBookDao {
    /**
     * 查找全部图书类型
     */
    public List<TypeBooks> findTypeBooks() {
        List<TypeBooks> list = null;
        String sql = "SELECT * FROM TYPE_BOOKS";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        ResultSetHandler<List<TypeBooks>> handler = new BeanListHandler<>(TypeBooks.class);
        try {
            list = se.executeQuery(sql, handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 添加图书类型
     */
    public int addTypeBook(TypeBooks typeBooks) {
        int row = 0;
        String sql = "INSERT INTO TYPE_BOOKS(TY_NAME)VALUES(?);";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        try {
            row = se.executeUpdate(sql, typeBooks.getTyName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    /**
     * 删除图书类型
     */
    public int deleteTypeBook(int tyId){
        int row =0;
        String sql = "DELETE FROM TYPE_BOOKS WHERE TY_ID = ?";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        try {
            se.executeUpdate(sql,tyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
