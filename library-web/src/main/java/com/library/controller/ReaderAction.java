package com.library.controller;

import com.library.dao.ReaderDao;
import com.library.dto.DataDto;
import com.library.dto.PageBean;
import com.library.exception.FlowException;
import com.library.model.Reader;
import com.library.service.ReaderManageService;
import org.evergreen.web.HttpStatus;
import org.evergreen.web.ViewResult;
import org.evergreen.web.annotation.RequestMapping;
import org.evergreen.web.view.Json;

import java.util.List;
import java.util.Map;

@RequestMapping("/reader")
public class ReaderAction {
    /**
     * 查看读者信息列表
     * @param currentPage
     * @return
     */
    @RequestMapping("/find")
    public ViewResult find(int currentPage){
        PageBean pageBean = new ReaderManageService().find(currentPage);
        return new Json(pageBean);
    }
    /**
     * 添加读者信息
     */
    @RequestMapping("/addReader")
    public ViewResult addReader(Reader re, int currentPage){
        DataDto data = new DataDto();
        try {
            PageBean pageBean = new ReaderManageService().addReader(re,currentPage);
            data.setStatusCode(HttpStatus.SC_OK);
            data.setValue(pageBean);
        }catch(FlowException e) {
            e.printStackTrace();
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data);
    }

    /**
     * 注销用户
     * @param reId
     * @param currentPage
     * @return
     */
    @RequestMapping("/deleteReader")
    public ViewResult deleteReader(int reId,int currentPage){
        System.out.println(reId+currentPage);
        DataDto data = new DataDto();
        try {
            PageBean pageBean = new ReaderManageService().deleteReader(reId,currentPage);
            data.setStatusCode(HttpStatus.SC_OK);
            data.setValue(pageBean);
        }catch(FlowException e) {
            e.printStackTrace();
            data.setMessage(e.getMessage());
            data.setStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return new Json(data);
    }

}
