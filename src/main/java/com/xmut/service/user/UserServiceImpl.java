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
    public List<User> queryUserByUserNo(User user) {
        return userDao.queryUserByUserNo(user);
    }

    @Override
    public int updateUserAccount(User user) {
        return userDao.updateUserAccount(user);
    }

    @Override
    public void updateUserBalance(User user) {
        userDao.updateUserBalance(user);
    }

    @Override
    public void updateUserInfo(User user) {
        userDao.updateUserInfo(user);
    }

    @Override
    public int updateUserPass(User user) {
        return userDao.updateUserPass(user);
    }

    @Override
    public int deleteUserByUserID(User user) {
        return userDao.deleteUserByUserID(user);
    }
}
