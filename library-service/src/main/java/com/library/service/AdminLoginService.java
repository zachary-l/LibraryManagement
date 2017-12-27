package com.library.service;

import com.library.dao.AdminDao;
import com.library.dao.exception.FlowException;
import com.library.model.Admin;
import com.library.utils.MD5Util;

public class AdminLoginService {
    //登陆
    public Admin checkLogin(Admin admin) {
        Admin ad = new AdminDao().findById(admin.getAdId());
        if (ad == null || !ad.getAdPass().equals(MD5Util.MD5Encode(admin.getAdPass(), "UTF-8"))) {
            throw new FlowException("用户名或密码错误", 401);
        }
        return ad;
    }

}
