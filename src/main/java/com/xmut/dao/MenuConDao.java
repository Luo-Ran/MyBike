package com.xmut.dao;

import com.xmut.pojo.MenuCon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuConDao {
    /**
     * 查询菜单权限
     * @param authority
     * @return
     */
    List<MenuCon> queryMenuControl(String authority);
}
