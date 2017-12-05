package com.library.model;

import org.evergreen.db.helper.Column;

/**
 * 图书类型表
 */
public class TypeBooks {
    @Column("TY_ID")//图书类型主键
    private int tyId;
    @Column("TY_NAME")//图书类型
    private String tyName;

    public int getTyId() {
        return tyId;
    }

    public void setTyId(int tyId) {
        this.tyId = tyId;
    }

    public String getTyName() {
        return tyName;
    }

    public void setTyName(String tyName) {
        this.tyName = tyName;
    }
}
