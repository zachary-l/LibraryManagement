package com.library.dao;

import com.library.model.RetisgerFind;
import com.library.model.ReturnBooks;
import com.library.utils.SQLExecutorFactory;
import org.evergreen.db.helper.SQLExecutor;

import java.sql.SQLException;

public class ReturnFindDao {
    /**
     * 添加归还记录
     */
    public int addBorrowFind(RetisgerFind re,ReturnBooks r){
        int row = 0;
        String sql = "INSERT INTO REGISTER_FINE(RF_EXPLAIN,RF_MONEY,TF_ID,RE_ID)VALUES(?,?,?,?);";
        SQLExecutor se = SQLExecutorFactory.getCurrentSQLExecutor();
        try {
            row = se.executeUpdate(sql,re.getRfExplain(),re.getRfMoney(),re.getTypeFind(),r.getBorId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
