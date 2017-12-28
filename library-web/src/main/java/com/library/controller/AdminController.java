package com.library.controller;

import com.library.exception.FlowException;
import com.library.dto.DataDto;
import com.library.model.Admin;
import com.library.service.AdminManageService;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.beans.util.ScanUtil;
import org.framework.mvc.ActionContext;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.JsonView;

import java.util.List;
import java.util.Set;

@RequestMapping("/admin")
@Component("adminController")
@Scope
public class AdminController {
    @Inject("adminManageService")
    private AdminManageService adminManageService;

    @RequestMapping("/findById")
    public ViewResult findById() {
        Admin admin = (Admin) ActionContext.getContext().getSessionMap().get("admin");
        return new JsonView(admin);
    }

    //显示图书管理员列表
    @RequestMapping("/findAll")
    public ViewResult find() {
        List<Admin> list = adminManageService.findAll();
        return new JsonView(list, "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 添加图书管理员
     */
    @RequestMapping("/addAdmin")
    public ViewResult addAdmin(Admin ad) {
        List<Admin> list = null;
        DataDto data = new DataDto();
        try {
            list = adminManageService.addAdmin(ad);
            data.setStatusCode(200);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data, "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 修改管理员信息
     */
    @RequestMapping("/updateAdmin")
    public ViewResult updateAdmin(Admin ad) {
        List<Admin> list = null;
        DataDto data = new DataDto();
        Admin admin = (Admin) ActionContext.getContext().getSessionMap().get("admin");
        ad.setAdId(admin.getAdId());
        try {
            list = adminManageService.updateAdmin(ad);
            data.setStatusCode(200);
        } catch (FlowException e) {
            e.printStackTrace();
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data, "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 修改密码
     */
    @RequestMapping("/updateAdmin")
    public ViewResult updatePass(Admin ad) {
        DataDto data = new DataDto();
        Admin admin = (Admin) ActionContext.getContext().getSessionMap().get("admin");
        ad.setAdId(admin.getAdId());
        try {
            new AdminManageService().updatePass(ad);
            data.setStatusCode(200);
        } catch (FlowException e) {
            e.printStackTrace();
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data);
    }

    /**
     * 修改图书管理员的状态
     */
    @RequestMapping("/revokeAdmin")
    public ViewResult revokeAdmin(Admin ad) {
        DataDto data = new DataDto();
        try {
            adminManageService.revokeAdmin(ad);
            data.setStatusCode(200);
        } catch (FlowException e) {
            e.printStackTrace();
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data, "yyyy-MM-dd hh:mm:ss");
    }

}
