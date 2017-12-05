package com.library.model;

import org.evergreen.db.helper.Column;

import java.util.Date;

/**
 * 借阅记录表
 */
public class BorrowBooks {
    @Column("BOR_ID")//借阅主键
    private int borId;
    @Column("BOR_TIME")//借书时间
    private Date borTime;
    @Column("BOR_TIME_LAST")//阅读期限
    private Date borTimeLast;
    @Column("BOR_NUM")//借阅册数
    private int borNum;
    @Column("IN_ID")//图书外键
    private int inId;
    @Column("RE_ID")//读者id外键
    private int reId;
    @Column("AD_ID")//图书管理员
    private int adId;

    public int getBorId() {
        return borId;
    }

    public void setBorId(int borId) {
        this.borId = borId;
    }

    public Date getBorTime() {
        return borTime;
    }

    public void setBorTime(Date borTime) {
        this.borTime = borTime;
    }

    public Date getBorTimeLast() {
        return borTimeLast;
    }

    public void setBorTimeLast(Date borTimeLast) {
        this.borTimeLast = borTimeLast;
    }

    public int getBorNum() {
        return borNum;
    }

    public void setBorNum(int borNum) {
        this.borNum = borNum;
    }

    public int getInId() {
        return inId;
    }

    public void setInId(int inId) {
        this.inId = inId;
    }

    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }
}
