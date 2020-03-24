package com.xmut.service.menu;

import com.xmut.pojo.Menu;

public interface MenuService {
    /**
     * 菜单信息查询
     * @param menuCode
     * @return
     */
    Menu queryMenuInfo(String menuCode);
}
