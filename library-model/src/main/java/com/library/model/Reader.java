package com.library.model;

import org.evergreen.db.helper.Column;

public class Reader {
    @Column("RE_ID")
    private int reId;//作者id
    @Column("RE_NAME")
    private String reName;//名字
    @Column("RE_SEX")
    private int sex;//性别
    @Column("RE_PHONE")
    private String phone;//电话
    @Column("RE_ADDRESS")
    private String reAddress;//地址
    @Column("RE_CREDIT")
    private int reCredit;//信用积分

    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReAddress() {
        return reAddress;
    }

    public void setReAddress(String reAddress) {
        this.reAddress = reAddress;
    }

    public int getReCredit() {
        return reCredit;
    }

    public void setReCredit(int reCredit) {
        this.reCredit = reCredit;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "reId='" + reId + '\'' +
                ", reName='" + reName + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", reAddress='" + reAddress + '\'' +
                ", reCredit=" + reCredit +
                '}';
    }
}
