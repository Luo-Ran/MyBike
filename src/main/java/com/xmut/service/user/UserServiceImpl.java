package com.xmut.service.user;

import com.xmut.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmut.pojo.User;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserByID(Long id) {
        return userDao.getUserByID(id);
    }

    @Override
    public List<User> findUsers(String userNo) {
        return userDao.findUsers(userNo);
    }

    @Override
    public User checkUserLogin(String userNo, String userPass) {
        return userDao.checkUserLogin(userNo, userPass);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public int updateAccountStatus(User user) {
        return userDao.updateAccountStatus(user);
    }

    @Override
    public void updateUserBalance(User user) {
        userDao.updateUserBalance(user);
    }

    @Override
    public void updateUserInfo(User user) {
        userDao.updateUserInfo(user);
    }
}
