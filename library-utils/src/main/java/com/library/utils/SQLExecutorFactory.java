package com.library.utils;

import org.evergreen.db.helper.SQLExecutor;

import java.sql.SQLException;

/**
 * 利用工厂的思想，由这个类来创建SQLExecutor实例
 * ，并且这个工厂创建出来的SQLExecutor是绑定到threadLocal的
 */
public class SQLExecutorFactory {
    //创建本地线程变量
    private static ThreadLocal<SQLExecutor> local = new ThreadLocal<>();

    //创建一个SQLExecutor,是绑定threadLocal中的
    public static SQLExecutor getCurrentSQLExecutor(){
        if(local.get()==null){
            local.set(new SQLExecutor(DBUtil.getConnection()));
        }
        return local.get();
    }
    //创建普通的SQLExecutor，并没有绑定到threadLocal
    public static SQLExecutor getSQLExecutor() throws SQLException{
        return new SQLExecutor(DBUtil.getConnection());
    }

    /**
     * 移除线程本地变量
     */
    public static void removeLocal(){
        if(local.get()!=null){
            local.remove();
        }
    }
}
