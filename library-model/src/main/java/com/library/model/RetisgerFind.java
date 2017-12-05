package com.library.model;

import org.evergreen.db.helper.Column;

/**
 * 罚金记录表
 */
public class RetisgerFind {
    @Column("RF_ID")//罚金记录编号
    private int rfId;
    @Column("RF_EXPLAIN")//罚金说明
    private String rfExplain;
    @Column("RF_MONEY")//罚金
    private double rfMoney;
    @Column("TF_ID")//外键罚金类型
    private int typeFind;
    @Column("RE_ID")//外键借阅记录
    private int tfId;

    public int getRfId() {
        return rfId;
    }

    public void setRfId(int rfId) {
        this.rfId = rfId;
    }

    public String getRfExplain() {
        return rfExplain;
    }

    public void setRfExplain(String rfExplain) {
        this.rfExplain = rfExplain;
    }

    public double getRfMoney() {
        return rfMoney;
    }

    public void setRfMoney(double rfMoney) {
        this.rfMoney = rfMoney;
    }

    public int getTypeFind() {
        return typeFind;
    }

    public void setTypeFind(int typeFind) {
        this.typeFind = typeFind;
    }

    public int getTfId() {
        return tfId;
    }

    public void setTfId(int tfId) {
        this.tfId = tfId;
    }
}
