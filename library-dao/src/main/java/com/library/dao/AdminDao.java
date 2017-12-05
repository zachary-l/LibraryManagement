package com.library.dao;

import com.library.model.Admin;
import com.library.utils.DBUtil;
import org.evergreen.db.helper.ResultSetHandler;
import org.evergreen.db.helper.SQLExecutor;
import org.evergreen.db.helper.handler.BeanHandler;
import org.evergreen.db.helper.handler.BeanListHandler;
import org.evergreen.db.helper.handler.ColumnHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDao {
    /**
     * 图书管理员登陆
     */
    public Admin findById(int adId){
        Admin ad = null;
        String sql = "SELECT * FROM ADMIN_USERS WHERE AD_ID = ?";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        ResultSetHandler<Admin> handler = new BeanHandler<>(Admin.class);
        try {
            ad = se.executeQuery(sql,handler,adId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return ad;
    }
    /**
     * 注册
     */
    public int addAdmin(Admin ad){
        int row = 0;
        String sql = "INSERT INTO ADMIN_USERS(AD_NAME,AD_PASS,AD_SEX,AD_PHONE,AD_JUR,AD_TIME) VALUES(?,?,?,?,?,?);";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());

        try {
            row = se.executeUpdate(sql,ad.getAdName(),ad.getAdPass(),ad.getAdSex(),ad.getAdPhone(),ad.getAdJur(),ad.getAdTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
    /**
     * 修改管理员身份
     */
    public int revokeAdmin(Admin ad){
        int row = 0;
        String sql = "UPDATE ADMIN_USERS SET AD_JUR=? WHERE AD_ID = ?";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());

        try {
            row = se.executeUpdate(sql,ad.getAdJur(),ad.getAdId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 修改密码
     */
    public int updatePass(Admin ad){
        int row = 0;
        String sql = "UPDATE ADMIN_USERS SET AD_PASS = ? WHERE AD_ID = ?";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        try {
            row = se.executeUpdate(sql,ad.getAdPass(),ad.getAdId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    /**
     * 修改管理员信息
     */
    public int updateAdmin(Admin ad){
        int row = 0;
        String sql = "UPDATE ADMIN_USERS SET AD_NAME = ?,AD_PASS = ?,AD_SEX = ?,AD_PHONE  =? WHERE AD_ID = ?";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        try {
            row = se.executeUpdate(sql,ad.getAdName(),ad.getAdPass(),ad.getAdSex(),ad.getAdPhone(),ad.getAdId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    /**
     * 查询总页数
     */
    public int count() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM ADMIN_USERS";
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
     * 分页查询
     */
    public List<Admin> findPage(int firstResult , int maxResult){
        List<Admin> ad = null;
        String sql  ="SELECT * FROM ADMIN_USERS DESC LIMIT ?,?;";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        ResultSetHandler<List<Admin>> handler = new BeanListHandler<>(Admin.class);
        try {
            ad = se.executeQuery(sql,handler,firstResult,maxResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ad;
    }

    /**
     * 查询全部
     */
    public List<Admin> find(){
        List<Admin> ad = null;
        String sql  ="SELECT * FROM ADMIN_USERS;";
        SQLExecutor se = new SQLExecutor(DBUtil.getConnection());
        ResultSetHandler<List<Admin>> handler = new BeanListHandler<>(Admin.class);
        try {
            ad = se.executeQuery(sql,handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ad;
    }
}
