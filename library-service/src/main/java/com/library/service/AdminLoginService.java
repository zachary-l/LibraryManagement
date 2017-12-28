package com.library.service;

import com.library.dao.AdminDao;
import com.library.exception.FlowException;
import com.library.model.Admin;
import com.library.utils.MD5Util;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;

@Component("adminLoginService")
public class AdminLoginService {
    @Inject("adminDao")
    private AdminDao adminDao;
    //登陆
    public Admin checkLogin(Admin admin) {
        Admin ad = adminDao.findById(admin.getAdId());
        if (ad == null || !ad.getAdPass().equals(MD5Util.MD5Encode(admin.getAdPass(), "UTF-8"))) {
            throw new FlowException("用户名或密码错误");
        }
        return ad;
    }


}
