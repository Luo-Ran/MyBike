package com.xmut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmut.common.Constans;
import com.xmut.common.Result;
import com.xmut.pojo.Menu;
import com.xmut.pojo.MenuCon;
import com.xmut.service.menu.MenuService;
import com.xmut.service.menuCon.MenuConService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menuCon")
public class MenuConController {
    @Autowired
    private MenuConService menuConService;
    @Autowired
    private MenuService menuService;

    /**
     * 菜单查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryTabbers", method = RequestMethod.POST )
    @ResponseBody
    public Result changeBikeStatus(@RequestBody JSONObject request){
        JSONObject json = JSON.parseObject(request.toJSONString());
        String accAuthority = json.getString("accAuthority");
        String menuType = json.getString("menuType");
        // 查询该用户拥有的权限
        List<MenuCon> menuConList = menuConService.queryMenuControl(accAuthority);
        // 根据menuType过滤部分菜单
        List<Menu> menuList = new ArrayList<>();
        for(MenuCon menuCon:menuConList){
            Menu menu = menuService.queryMenuInfo(menuCon.getMenuCode());
            // 主菜单
            if(Constans.MENU_TYPE.PARENT_MENU.equals(menuType)) {
                if(Constans.MENU_TYPE.PARENT_SUPERIOR_MENU.equals(menu.getSuperiorMenu())){
                    menuList.add(menu);
                }
            }else {
                if(!Constans.MENU_TYPE.PARENT_SUPERIOR_MENU.equals(menu.getSuperiorMenu())){
                    menuList.add(menu);
                }
            }
        }
        Result result = new Result();
        result.setData(menuList);
        return result;
    }
}
