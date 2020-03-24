package com.xmut.service.menuCon;

import com.xmut.dao.MenuConDao;
import com.xmut.pojo.MenuCon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuConServiceImpl implements MenuConService {
    @Autowired
    private MenuConDao menuConDao;

    @Override
    public List<MenuCon> queryMenuControl(String authority) {
        return menuConDao.queryMenuControl(authority);
    }
}
