package com.xmut.dao;

import com.xmut.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    /**
     * orderId删除数据
     * @param orderId
     * @return
     */
    int deleteOrderInfoByOrderId(String orderId);
    /**
     * 新增订单
     * @param order
     */
    void saveBikeOrderInfo(Order order);
    /**
     * 根据订单号查询订单
     * @param orderId
     * @return
     */
    Order queryOrderByOrderId(String orderId);

    /**
     * 完成订单--更新数据
     * @param order
     */
    void updateBikeOrderInfo(Order order);

    /**
     * 统计某一站点在某一日期的订单数量
     * @param time
     * @param siteId
     * @return
     */
    int countBikeOrderByTime(String time, Long siteId);

    /**
     * 查询某一站点在某一日期的订单
     * @param time
     * @param siteId
     * @return
     */
    List<Order> orderProfitByTime(String time, Long siteId);
}
