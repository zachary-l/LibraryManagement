package com.library.model;

import org.evergreen.db.helper.Column;

import java.util.Date;

/**
 * 图书管理员
 */
public class Admin {
    @Column("AD_ID")
    private int adId;//管理员编号
    @Column("AD_NAME")
    private String adName;//姓名
    @Column("AD_PASS")
    private String adPass;//密码
    @Column("AD_SEX")
    private int adSex;//性别
    @Column("AD_PHONE")
    private String adPhone;//电话
    @Column("AD_JUR")
    private int adJur;//权限
    @Column("AD_TIME")
    private Date adTime;//注册时间

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdPass() {
        return adPass;
    }

    public void setAdPass(String adPass) {
        this.adPass = adPass;
    }

    public int getAdSex() {
        return adSex;
    }

    public void setAdSex(int adSex) {
        this.adSex = adSex;
    }

    public String getAdPhone() {
        return adPhone;
    }

    public void setAdPhone(String adPhone) {
        this.adPhone = adPhone;
    }

    public int getAdJur() {
        return adJur;
    }

    public void setAdJur(int adJur) {
        this.adJur = adJur;
    }

    public Date getAdTime() {
        return adTime;
    }

    public void setAdTime(Date adTime) {
        this.adTime = adTime;
    }
}
