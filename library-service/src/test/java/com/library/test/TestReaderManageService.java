package com.library.test;

import com.library.dto.PageBean;
import com.library.model.Reader;
import com.library.service.ReaderManageService;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestReaderManageService {
    @Test
    public void findTest() {
        ReaderManageService service = new ReaderManageService();
        PageBean pageBean = service.find(1);
        Object list = pageBean.getList();
        System.out.println(list);
    }
    @Test
    public void addTest() {
        Reader reader = new Reader();
        reader.setReName("list");
        reader.setSex(1);
        reader.setReCredit(100);
        reader.setPhone("15412135464");
        reader.setReAddress("fsf");
        ReaderManageService service = new ReaderManageService();
        service.addReader(reader,1);

    }
}
