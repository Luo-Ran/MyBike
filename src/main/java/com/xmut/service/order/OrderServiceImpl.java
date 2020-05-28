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

    @Override
    public int deleteOrderInfoByOrderId(String orderId) {
        return orderDao.deleteOrderInfoByOrderId(orderId);
    }

    @Override
    public void saveBikeOrderInfo(Order order) {
        orderDao.saveBikeOrderInfo(order);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        return orderDao.queryOrderByOrderId(orderId);
    }

    @Override
    public void updateBikeOrderInfo(Order order) {
        orderDao.updateBikeOrderInfo(order);
    }

    @Override
    public int countBikeOrderByTime(String time, Long siteId) {
        return orderDao.countBikeOrderByTime(time,siteId);
    }

    @Override
    public List<Order> orderProfitByTime(String time, Long siteId) {
        return orderDao.orderProfitByTime(time, siteId);
    }

}
