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
    User getUserByID(int id);

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
}
