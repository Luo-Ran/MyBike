package com.xmut.dao;

import com.xmut.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDao {

    /**
     * 获取该用户的订单
     * @param userId
     * @return
     */
    List<Order> getOrderByUserID(Long userId);
}
