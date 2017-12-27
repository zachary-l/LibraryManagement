package com.library.controller;

import com.library.dao.exception.FlowException;
import com.library.dto.DataDto;
import com.library.model.Admin;
import com.library.service.AdminLoginService;
import org.evergreen.web.ActionContext;
import org.evergreen.web.HttpStatus;
import org.evergreen.web.ViewResult;
import org.evergreen.web.annotation.RequestMapping;
import org.evergreen.web.view.Json;

public class LoginController {
    //登陆请求处理
    @RequestMapping("/browserlogin")
    public ViewResult browserLogin(Admin ad) {
        Admin admin = null;
        DataDto data = new DataDto();
        try {
            admin = new AdminLoginService().checkLogin(ad);
            ActionContext.getContext().getSession().put("admin", admin);
            data.setStatusCode(HttpStatus.SC_OK);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data);
    }
}
