package com.library.controller;

import com.library.dto.DataDto;
import com.library.exception.FlowException;
import com.library.model.TypeBooks;
import com.library.service.TypeBooksService;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.mvc.ViewResult;
import org.framework.mvc.ann.RequestMapping;
import org.framework.mvc.view.JsonView;

import java.util.List;

@RequestMapping("/typeBook")
@Component("typeBookController")
@Scope
public class TypeBookController {
    @Inject("typeBooksService")
    private TypeBooksService typeBooksService;

    /**
     * 查看图书类型信息
     * @return
     */
    @RequestMapping("/findType")
    public ViewResult findTypeBooks() {
        return new JsonView(typeBooksService.findTypeBooks());
    }

    /**
     * 添加图书类型
     * @param typeBooks
     * @return
     */
    @RequestMapping("/addfindType")
    public ViewResult addFindType(TypeBooks typeBooks){
        DataDto dataDto = new DataDto();
        List<TypeBooks> list=null;
        try {
             list=typeBooksService.addFindType(typeBooks);
            dataDto.setValue(list);
            dataDto.setStatusCode(200);
        } catch (FlowException e) {
            dataDto.setMessage(e.getMessage());
            dataDto.setStatusCode(401);
        }
        return new JsonView(list);
    }

    /**
     * 删除图书类型信息
     * @param tyId
     * @return
     */
    @RequestMapping("/deleteFindType")
    public ViewResult deleteFindType(int tyId){
        DataDto dataDto = new DataDto();
        List<TypeBooks> list=null;
        try {
            list=typeBooksService.deleteFindType(tyId);
            dataDto.setValue(list);
            dataDto.setStatusCode(200);
        } catch (FlowException e) {
            dataDto.setMessage(e.getMessage());
            dataDto.setStatusCode(401);
        }
        return new JsonView(list);
    }

}
