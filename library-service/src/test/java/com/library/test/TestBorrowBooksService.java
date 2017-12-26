package com.library.test;

import com.library.dto.PageBean;
import com.library.service.BorrowBooksService;
import org.junit.Test;

import java.util.Map;

public class TestBorrowBooksService {
    @Test
    public void findBorrowListTest(){
        BorrowBooksService borrowBooksService = new BorrowBooksService();
        PageBean pageBean = borrowBooksService.findBorrowList(1);
        Object map = pageBean.getList();
        System.out.println(map.toString());
    }
}
