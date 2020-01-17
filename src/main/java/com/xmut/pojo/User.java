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
}
