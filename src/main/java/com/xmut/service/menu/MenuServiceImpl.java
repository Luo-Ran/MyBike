package com.xmut.service.menu;

import com.xmut.dao.MenuDao;
import com.xmut.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public Menu queryMenuInfo(String menuCode) {
        return menuDao.queryMenuInfo(menuCode);
    }
}
