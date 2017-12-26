package com.library.test;

import com.library.dao.BorrowBooksDao;
import com.sun.javafx.collections.MappingChange;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestBorrowBooksDao {
    @Test
    public void findTest(){
        BorrowBooksDao dao  =new BorrowBooksDao();
        List<Map<String,Object>> list = dao.findBorrow(0,10);
        for(Map<String,Object> li:list){
            for(String o:li.keySet()){
                System.out.println("o-->"+li.get(o));
            }
        }
    }
}
