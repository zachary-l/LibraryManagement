package com.library.model;

import org.evergreen.db.helper.Column;

/**
 * 罚金类型表
 */
public class TypeFind {
    @Column("TF_ID")//罚金类型主键
    private int tfId;
    @Column("TF_TYPE")//罚金类型
    private String tfType;

    public int getTfId() {
        return tfId;
    }

    public void setTfId(int tfId) {
        this.tfId = tfId;
    }

    public String getTfType() {
        return tfType;
    }

    public void setTfType(String tfType) {
        this.tfType = tfType;
    }
}
