package com.library.controller;

import com.library.dao.exception.FlowException;
import com.library.dto.DataDto;
import com.library.model.Admin;
import com.library.service.AdminManageService;
import org.evergreen.web.ActionContext;
import org.evergreen.web.HttpStatus;
import org.evergreen.web.ViewResult;
import org.evergreen.web.annotation.RequestMapping;
import org.evergreen.web.view.Json;

import java.util.List;

@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/findById")
    public ViewResult findById() {
        Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
        return new Json(admin);
    }

    //显示图书管理员列表
    @RequestMapping("/findAll")
    public ViewResult find() {
        List<Admin> list = new AdminManageService().findAll();
        return new Json(list, "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 添加图书管理员
     */
    @RequestMapping("/addAdmin")
    public ViewResult addAdmin(Admin ad) {
        List<Admin> list = null;
        DataDto data = new DataDto();
        try {
            list = new AdminManageService().addAdmin(ad);
            data.setStatusCode(HttpStatus.SC_OK);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data, "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 修改管理员信息
     */
    @RequestMapping("/updateAdmin")
    public ViewResult updateAdmin(Admin ad) {
        List<Admin> list = null;
        DataDto data = new DataDto();
        Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
        ad.setAdId(admin.getAdId());
        try {
            list = new AdminManageService().updateAdmin(ad);
            data.setStatusCode(HttpStatus.SC_OK);
        } catch (FlowException e) {
            e.printStackTrace();
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data, "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 修改密码
     */
    @RequestMapping("/updateAdmin")
    public ViewResult updatePass(Admin ad) {
        DataDto data = new DataDto();
        Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
        ad.setAdId(admin.getAdId());
        try {
            new AdminManageService().updatePass(ad);
            data.setStatusCode(HttpStatus.SC_OK);
        } catch (FlowException e) {
            e.printStackTrace();
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data);
    }

    /**
     * 修改图书管理员的状态
     */
    @RequestMapping("/revokeAdmin")
    public ViewResult revokeAdmin(Admin ad) {
        DataDto data = new DataDto();
        try {
            new AdminManageService().revokeAdmin(ad);
            data.setStatusCode(HttpStatus.SC_OK);
        } catch (FlowException e) {
            e.printStackTrace();
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data, "yyyy-MM-dd hh:mm:ss");
    }

}
