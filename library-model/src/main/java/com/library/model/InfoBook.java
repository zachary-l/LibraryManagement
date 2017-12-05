package com.library.model;

import org.evergreen.db.helper.Column;

import java.util.Date;

/**
 * 读者信息表
 */
public class InfoBook {
    @Column("IN_ID")//图书编号
    private int inId;
    @Column("IN_NAME")//图书名字
    private String inName;
    @Column("IN_AUTHOR")//图书作者
    private String inAuthor;
    @Column("IN_PUBLICATIONTIME")//出版时间
    private Date inPubliCationTime;
    @Column("IN_UPLIBRARYTIME")//入馆时间
    private Date inUplibraryTime;
    @Column("IN_UPDATETIME")//最近更新时间
    private Date inUpdateTime;
    @Column("IN_PRESS")//出版社
    private String inPress;
    @Column("IN_PRICE")//价格
    private double inPrice;
    @Column("IN_NUM")//数量
    private int inNum;
    @Column("IN_BORROWFROM")//是否允许外借
    private int inBorrowFrom;
    @Column("TY_ID")//图书类型外键
    private int tyId;

    public int getInId() {
        return inId;
    }

    public void setInId(int inId) {
        this.inId = inId;
    }

    public String getInName() {
        return inName;
    }

    public void setInName(String inName) {
        this.inName = inName;
    }

    public String getInAuthor() {
        return inAuthor;
    }

    public void setInAuthor(String inAuthor) {
        this.inAuthor = inAuthor;
    }

    public Date getInPubliCationTime() {
        return inPubliCationTime;
    }

    public void setInPubliCationTime(Date inPubliCationTime) {
        this.inPubliCationTime = inPubliCationTime;
    }

    public Date getInUplibraryTime() {
        return inUplibraryTime;
    }

    public void setInUplibraryTime(Date inUplibraryTime) {
        this.inUplibraryTime = inUplibraryTime;
    }

    public Date getInUpdateTime() {
        return inUpdateTime;
    }

    public void setInUpdateTime(Date inUpdateTime) {
        this.inUpdateTime = inUpdateTime;
    }

    public String getInPress() {
        return inPress;
    }

    public void setInPress(String inPress) {
        this.inPress = inPress;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public int getInNum() {
        return inNum;
    }

    public void setInNum(int inNum) {
        this.inNum = inNum;
    }

    public int getInBorrowFrom() {
        return inBorrowFrom;
    }

    public void setInBorrowFrom(int inBorrowFrom) {
        this.inBorrowFrom = inBorrowFrom;
    }

    public int getTyId() {
        return tyId;
    }

    public void setTyId(int tyId) {
        this.tyId = tyId;
    }
}
