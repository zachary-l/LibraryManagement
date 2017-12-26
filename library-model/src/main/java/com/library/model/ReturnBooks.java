package com.library.model;

import org.evergreen.db.helper.Column;

import java.util.Date;

public class ReturnBooks {
    @Column("RET_ID")//归还记录主键
    private int retId;
    @Column("RET_TIME")//归还时间
    private Date retTime;
    @Column("RET_NUM")//归还数量
    private int retNum;
    @Column("BOR_ID")//借阅id外键
    private int borId;

    public int getRetId() {
        return retId;
    }

    public void setRetId(int retId) {
        this.retId = retId;
    }

    public Date getRetTime() {
        return retTime;
    }

    public void setRetTime(Date retTime) {
        this.retTime = retTime;
    }

    public int getRetNum() {
        return retNum;
    }

    public void setRetNum(int retNum) {
        this.retNum = retNum;
    }

    public int getBorId() {
        return borId;
    }

    public void setBorId(int borId) {
        this.borId = borId;
    }
}
