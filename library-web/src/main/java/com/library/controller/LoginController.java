package com.library.controller;

import com.library.exception.FlowException;
import com.library.dto.DataDto;
import com.library.model.Admin;
import com.library.service.AdminLoginService;
import com.library.service.AdminManageService;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.mvc.ActionContext;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.JsonView;
@Component("loginController")
@Scope
public class LoginController {
    @Inject("adminLoginService")
    private AdminLoginService adminLoginService;
    //登陆请求处理
    @RequestMapping("/browserlogin")
    public ViewResult browserLogin(Admin ad) {
        Admin admin = null;
        DataDto data = new DataDto();
        try {
            admin = adminLoginService.checkLogin(ad);
            ActionContext.getContext().getSessionMap().put("admin", admin);
            data.setStatusCode(200);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data);
    }
}
