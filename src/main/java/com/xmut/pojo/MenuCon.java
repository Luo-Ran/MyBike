package com.xmut.pojo;

import java.io.Serializable;

/**
 * 菜单权限表
 */
public class MenuCon implements Serializable {
    private static final long serialVersionUID = 2828330154695283803L;

    private Long id;
    /**
     * 菜单编码
     */
    private String menuCode;
    /**
     * 账户权限（1.管理员 2.普通用户）
     */
    private String accountAuthority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getAccountAuthority() {
        return accountAuthority;
    }

    public void setAccountAuthority(String accountAuthority) {
        this.accountAuthority = accountAuthority;
    }
}
