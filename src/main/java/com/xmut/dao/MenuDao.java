package com.xmut.dao;

import com.xmut.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MenuDao {
    /**
     * 菜单信息查询
     * @param menuCode
     * @return
     */
    Menu queryMenuInfo(String menuCode);
}
