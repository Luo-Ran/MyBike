package com.xmut.service.menuCon;

import com.xmut.pojo.MenuCon;

import java.util.List;

public interface MenuConService {
    /**
     * 查询菜单权限
     * @param authority
     * @return
     */
    List<MenuCon> queryMenuControl(String authority);
}
