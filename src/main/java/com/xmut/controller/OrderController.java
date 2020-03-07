package com.xmut.controller;

import com.alibaba.fastjson.JSON;
import com.xmut.common.Constans;
import com.xmut.common.Result;
import com.xmut.pojo.*;
import com.xmut.pojo.Order;
import com.xmut.pojo.Params.OrderResponse;
import com.xmut.service.order.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getOrderByUserID", method = RequestMethod.POST)
    @ResponseBody
    public Result getOrderByUserID(HttpServletRequest request){
        Result result = new Result();
        String user  = request.getParameter("user");
        User u =  new User();
        u = JSON.parseObject(user,User.class);
        List<Order> orderList = orderService.getOrderByUserID(u.getUserId());
        List<OrderResponse> responseList = new ArrayList<>();
        for(Order o:orderList){
            OrderResponse orderResponse = new OrderResponse();
            // 父类对象数值传给子类
            copyOrder(o,orderResponse);
            if(Constans.ORDER_STATUS.Order_Status_0.equals(o.getStatus())){
                orderResponse.setStatusDesc(Constans.ORDER_STATUS.Order_Status_0_DESC);
            }else if(Constans.ORDER_STATUS.Order_Status_1.equals(o.getStatus())){
                orderResponse.setStatusDesc(Constans.ORDER_STATUS.Order_Status_1_DESC);
            }
            // 计算骑行时间差
            Long seconds = (o.getEndTime().getTime() - o.getStartTime().getTime()) / 1000;
            Long second =  seconds % 60;
            Long minutes = (seconds - second) / 60;
            Long mintue = minutes % 60;
            Long hour = (minutes - mintue) / 60;
            StringBuffer tripTimeDesc = new StringBuffer();
            if(0 != hour){
                tripTimeDesc.append(hour);
                tripTimeDesc.append("小时");
            }
            if(0 != mintue){
                tripTimeDesc.append(mintue);
                tripTimeDesc.append("分钟");
            }
            if(0 != second){
                tripTimeDesc.append(second);
                tripTimeDesc.append("秒");
            }
            orderResponse.setTripTme(seconds);
            orderResponse.setTripTimeDesc(tripTimeDesc.toString());
            responseList.add(orderResponse);
        }
        result.setData(responseList);
        return result;
    }

    private void copyOrder(Order order,OrderResponse orderResponse){
        orderResponse.setId(order.getId());
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setStartTime(order.getStartTime());
        orderResponse.setEndTime(order.getEndTime());
        orderResponse.setTripDist(order.getTripDist());
        orderResponse.setRentalCost(order.getRentalCost());
        orderResponse.setBikeId(order.getBikeId());
    }
}
