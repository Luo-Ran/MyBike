package com.xmut.dao;
import com.xmut.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Administrator
 */
@Mapper
@Repository
public interface UserDao {
    /**
     * ID查询
     * @param id
     * @return
     */
    User getUserByID(Long id);

    /**
     * 用户名模糊查询
     * @param userNo
     * @return
     */
    List<User> findUsers(String userNo);

    /**
     * 判断登录
     * @param userNo
     * @param userPass
     * @return
     */
    User checkUserLogin(String userNo, String userPass);

    /**
     * 插入一个用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 查询所有账号
     * @return
     */
    List<User> queryAllUser();

    /**
     * 账号禁用
     * @param user
     * @return
     */
    int updateAccountStatus(User user);

    /**
     * 更新账户余额
     * @param user
     */
    void updateUserBalance(User user);

    /**
     * 更新账户资料
     * @param user
     */
    void updateUserInfo(User user);
}
