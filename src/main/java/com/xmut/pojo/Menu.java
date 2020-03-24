package com.xmut.pojo;

import java.io.Serializable;

public class Menu implements Serializable {
    private static final long serialVersionUID = -6794086594948831734L;
    private Long id;
    /**
     * 菜单路由
     */
    private String menuName;
    /**
     * 菜单icon
     */
    private String menuClass;
    /**
     * 菜单名称
     */
    private String menuTitle;
    /**
     * 菜单编码
     */
    private String menuCode;
    /**
     * 上级菜单
     */
    private String superiorMenu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuClass() {
        return menuClass;
    }

    public void setMenuClass(String menuClass) {
        this.menuClass = menuClass;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getSuperiorMenu() {
        return superiorMenu;
    }

    public void setSuperiorMenu(String superiorMenu) {
        this.superiorMenu = superiorMenu;
    }
}
