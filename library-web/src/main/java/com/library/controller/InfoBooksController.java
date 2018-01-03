package com.library.controller;

import com.library.exception.FlowException;
import com.library.dto.DataDto;
import com.library.dto.PageBean;
import com.library.model.InfoBook;
import com.library.model.TypeBooks;
import com.library.service.InfoBooksService;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.ForwardView;
import org.framework.mvc.view.JsonView;

@RequestMapping("/infoBooks")
@Component("infoBooksController")
@Scope
public class InfoBooksController {
    @Inject("infoBooksService")
    private InfoBooksService infoBooksService;
    /**
     * 查询全部
     */
    @RequestMapping("/findBooks")
    public ViewResult findBook(int currentPage) {
        return new JsonView(infoBooksService.findInfoBooks(currentPage), "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 根据图书类型查询
     */
    @RequestMapping("/findBooksById")
    public ViewResult findBooksById(TypeBooks typeBooks, int currentPage) {
        if (typeBooks.getTyId() == 0) {
            return new ForwardView("addBooks");
        } else {
            return new JsonView(infoBooksService.findInfoBooksById(typeBooks, currentPage), "yyyy-MM-dd hh:mm:ss");
        }
    }

    /**
     * 添加书籍
     */
    @RequestMapping("/addBooks")
    public ViewResult addBook(InfoBook book, int currentPage) {
        DataDto data = new DataDto();
        try {
            PageBean pageBean = infoBooksService.addInfoBook(book, currentPage);
            data.setStatusCode(200);
            data.setValue(pageBean);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data, "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 删除图书
     * @param inId
     * @param currentPage
     * @return
     */
    @RequestMapping("/deleteBooks")
    public ViewResult deleteBooks(int inId,int currentPage){
        DataDto data = new DataDto();
        try {
            PageBean pageBean = infoBooksService.deleteIndoBook(inId,currentPage);
            data.setStatusCode(200);
            data.setValue(pageBean);
        } catch (FlowException e) {
            data.setMessage(e.getMessage());
            data.setStatusCode(401);
        }
        return new JsonView(data, "yyyy-MM-dd hh:mm:ss");
    }
}
