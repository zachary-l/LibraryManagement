package com.library.controller;

import com.library.exception.FlowException;
import com.library.dto.DataDto;
import com.library.dto.PageBean;
import com.library.model.Reader;
import com.library.service.ReaderManageService;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.JsonView;

@RequestMapping("/reader")
@Component("readerController")
@Scope
public class ReaderController {
    @Inject("readerManageService")
    private ReaderManageService readerManageService;
    /**
     * 查看读者信息列表
     *
     * @param currentPage
     * @return
     */
    @RequestMapping("/find")
    public ViewResult find(int currentPage) {
        PageBean pageBean = readerManageService.find(currentPage);
        return new JsonView(pageBean);
    }

    /**
     * 添加读者信息
     */
    @RequestMapping("/addReader")
    public ViewResult addReader(Reader re, int currentPage) {
        DataDto data = new DataDto();
        try {
            PageBean pageBean = readerManageService.addReader(re, currentPage);
            data.setStatusCode(200);
            data.setValue(pageBean);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data);
    }

    /**
     * 注销用户
     *
     * @param reId
     * @param currentPage
     * @return
     */
    @RequestMapping("/deleteReader")
    public ViewResult deleteReader(int reId, int currentPage) {
        System.out.println(reId + currentPage);
        DataDto data = new DataDto();
        try {
            PageBean pageBean = readerManageService.deleteReader(reId, currentPage);
            data.setStatusCode(200);
            data.setValue(pageBean);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data);
    }
}
