package com.library.service;

import com.library.dao.TypeBookDao;
import com.library.exception.FlowException;
import com.library.model.TypeBooks;
import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;

import java.util.List;
@Component("typeBooksService")
public class TypeBooksService {
    @Inject("typeBookDao")
    private TypeBookDao typeBookDao;
    public List<TypeBooks> findTypeBooks() {
        List<TypeBooks> list = typeBookDao.findTypeBooks();
        return list;
    }
    /**
     * 添加书籍类型
     */

    public List<TypeBooks> addFindType(TypeBooks typeBooks){
        int row = 0;
        row = typeBookDao.addTypeBook(typeBooks);
        if(row ==0){
            throw new FlowException("添加书籍失败");
        }
        return typeBookDao.findTypeBooks();
    }

    /**
     * 删除
     */
    public List<TypeBooks> deleteFindType(int tyId){
        int row = 0;
        row = typeBookDao.deleteTypeBook(tyId);
        if(row ==0){
            throw new FlowException("删除书籍失败");
        }
        return typeBookDao.findTypeBooks();
    }

}
