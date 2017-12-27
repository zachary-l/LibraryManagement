package com.library.service;

import com.library.dao.AdminDao;
import com.library.dao.exception.FlowException;
import com.library.model.Admin;

import java.util.List;

public class AdminManageService {
    /**
     * 查询全部管理员
     *
     * @return
     */
    public List<Admin> findAll() {
        List<Admin> list = new AdminDao().find();
        return list;
    }

    /**
     * 添加图书管理员
     */
    public List<Admin> addAdmin(Admin ad) {
        List<Admin> list = null;
        AdminDao dao = new AdminDao();
        int row = dao.addAdmin(ad);
        if (row == 0) {
            throw new FlowException("添加管理员信息失败", 401);
        } else {
            return dao.find();
        }
    }

    /**
     * 修改图书管理员信息
     */
    public List<Admin> updateAdmin(Admin ad) {
        List<Admin> list = null;
        AdminDao dao = new AdminDao();
        int row = dao.updateAdmin(ad);
        if (row == 0) {
            throw new FlowException("修改管理员信息失败", 401);
        } else {
            return dao.find();
        }
    }

    /**
     * 修改个人密码
     */
    public void updatePass(Admin ad) {
        AdminDao dao = new AdminDao();
        int row = dao.updatePass(ad);
        if (row == 0) {
            throw new FlowException("修改密码失败", 401);
        }
    }

    /**
     * 修改图书管理员的状态
     */
    public List<Admin> revokeAdmin(Admin ad) {
        List<Admin> list = null;
        AdminDao dao = new AdminDao();
        int row = dao.revokeAdmin(ad);
        if (row == 0) {
            throw new FlowException("修改管理员信息失败", 401);
        } else {
            return dao.find();
        }
    }


}
