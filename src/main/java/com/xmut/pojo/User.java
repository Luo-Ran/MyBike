package com.xmut.pojo;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class User implements Serializable {
    private static final long serialVersionUID = -3253370436493950475L;
    private Long userId;
    private String userNo;
    private String userPass;
    private String userName;
    private String userSign;
    private String accountAuthority;
    private String userBalance;
    private String accountStatus;
    private String accountStatusDesc;
    private String userSex;
    private String birthday;
    private String headImg;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getAccountAuthority() {
        return accountAuthority;
    }

    public void setAccountAuthority(String accountAuthority) {
        this.accountAuthority = accountAuthority;
    }

    public String getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(String userBalance) {
        this.userBalance = userBalance;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountStatusDesc() {
        return accountStatusDesc;
    }

    public void setAccountStatusDesc(String accountStatusDesc) {
        this.accountStatusDesc = accountStatusDesc;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
