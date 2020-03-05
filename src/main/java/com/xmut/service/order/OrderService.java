package com.xmut.service.order;

import com.xmut.pojo.Order;

import java.util.List;

public interface OrderService {
    /**
     * 获取该用户的订单
     * @param userId
     * @return
     */
    List<Order> getOrderByUserID(Long userId);
}
