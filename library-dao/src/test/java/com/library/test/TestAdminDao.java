package com.library.test;

import com.library.dao.AdminDao;
import com.library.dao.ReaderDao;
import com.library.model.Admin;
import com.library.utils.MD5Util;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestAdminDao {
    @Test
    public void findByIdest(){
        Admin admin = new Admin();
        admin.setAdId(600002);
        admin.setAdName("zachary");
        admin.setAdPass("123456");
        AdminDao dao = new AdminDao();
        Admin ad = dao.findById(admin.getAdId());
        System.out.println(ad.getAdName());
    }
    @Test
    public void findTest(){
        List<Admin> list = new AdminDao().find();
        for(Admin l:list){
            System.out.println(l);
        }
    }
    @Test
    public void addAdminTest(){
        Admin ad = new Admin();
        ad.setAdId(600001);
        ad.setAdPass(MD5Util.MD5Encode("123456","UTF-8"));
        ad.setAdName("lzg");
        ad.setAdPhone("1344487");
        ad.setAdJur(2);
        ad.setAdSex(1);
        ad.setAdTime(new Date());
        AdminDao dao = new AdminDao();
        //添加管理员
        int row = dao.addAdmin(ad);
        //修改
//        int row = ReaderManagementDao.revokeAdmin(ad);
//        int row  = ReaderManagementDao.updateAdmin(ad);
//        int row = ReaderManagementDao.updatePass(ad);
        System.out.println(row);
    }
    @Test
    public void count(){
        AdminDao dao = new AdminDao();
        System.out.println(dao.count());
    }
    @Test
    public void findReaderDaoTest(){
        ReaderDao dao = new ReaderDao();
        List<Map<String,Object>> list = dao.findReader(1,1);
        for(Map l:list){
            for(Object m:l.keySet()){
                System.out.println(m+"-->"+l.get(m));
            }
        }
    }
    @Test
    public void deteleReaderTest(){
        int row = new ReaderDao().deleteReader(500002);
        System.out.println(row);
    }
}
