package com.xmut.service.order;

import com.xmut.dao.OrderDao;
import com.xmut.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getOrderByUserID(Long userId) {
        return orderDao.getOrderByUserID(userId);
    }
}
