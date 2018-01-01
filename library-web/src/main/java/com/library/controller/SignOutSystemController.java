package com.library.controller;

import com.library.model.Admin;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Scope;
import org.framework.mvc.ActionContext;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.JsonView;
import org.framework.mvc.view.RedirectView;


@Component("signOutSystemController")
@Scope
public class SignOutSystemController {
    @RequestMapping("/signOut")
    public ViewResult signOut(){
        ActionContext.getContext().getSessionMap().remove("admin");
        return new JsonView("login.html");
    }
}
