package com.library.service;

import com.library.dao.AdminDao;
import com.library.exception.FlowException;
import com.library.model.Admin;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;

import java.util.List;
@Component("adminManageService")
@Scope
public class AdminManageService {
    @Inject("adminDao")
    private AdminDao adminDao;
    /**
     * 查询全部管理员
     *
     * @return
     */
    public List<Admin> findAll() {
        List<Admin> list = adminDao.find();
        return list;
    }

    /**
     * 添加图书管理员
     */
    public List<Admin> addAdmin(Admin ad) {
        List<Admin> list = null;
        int row = adminDao.addAdmin(ad);
        if (row == 0) {
            throw new FlowException("添加管理员信息失败");
        } else {
            return adminDao.find();
        }
    }

    /**
     * 修改图书管理员信息
     */
    public List<Admin> updateAdmin(Admin ad) {
        List<Admin> list = null;
        int row = adminDao.updateAdmin(ad);
        if (row == 0) {
            throw new FlowException("修改管理员信息失败");
        } else {
            return adminDao.find();
        }
    }

    /**
     * 修改个人密码
     */
    public void updatePass(Admin ad) {
        int row = adminDao.updatePass(ad);
        if (row == 0) {
            throw new FlowException("修改密码失败");
        }
    }

    /**
     * 修改图书管理员的状态
     */
    public List<Admin> revokeAdmin(Admin ad) {
        List<Admin> list = null;
        int row = adminDao.revokeAdmin(ad);
        if (row == 0) {
            throw new FlowException("修改管理员信息失败");
        } else {
            return adminDao.find();
        }
    }


}
