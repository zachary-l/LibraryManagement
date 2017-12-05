package com.library.test;

import com.library.model.Admin;
import com.library.service.AdminLoginService;
import org.junit.Test;

public class TestAdminService {
    @Test
    public void adminServiceTest(){
        Admin ad = new Admin();
        ad.setAdId(600000);
        ad.setAdPass("123456");
        AdminLoginService service = new AdminLoginService();
        Admin admin = service.checkLogin(ad);
        System.out.println(admin.getAdId());
        System.out.println(admin.getAdName());

    }
}
