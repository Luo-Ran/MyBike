package com.xmut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmut.common.BikeSystemUtil;
import com.xmut.common.Constans;
import com.xmut.common.Result;
import com.xmut.pojo.*;
import com.xmut.pojo.Order;
import com.xmut.pojo.Params.OrderResponse;
import com.xmut.service.bike.BikeService;
import com.xmut.service.expenseDetail.ExpenseDetailService;
import com.xmut.service.order.OrderService;
import com.xmut.service.site.SiteService;
import com.xmut.service.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BikeService bikeService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private UserService userService;
    @Autowired
    private ExpenseDetailService expenseDetailService;

    private static Double halfHour = 30.0;
    private static Double perPrice = 1.5;
    @RequestMapping(value = "/getOrderByUserID", method = RequestMethod.POST)
    @ResponseBody
    public Result getOrderByUserID(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String user  = jsonObject.getString("user");
        User u =  new User();
        u = JSON.parseObject(user,User.class);
        List<Order> orderList = orderService.getOrderByUserID(u.getUserId());
        List<OrderResponse> responseList = new ArrayList<>();
        for(Order o:orderList){
            OrderResponse orderResponse = new OrderResponse();
            // 父类对象数值传给子类
            copyOrder(o,orderResponse);
            // 使用结束
            if(Constans.ORDER_STATUS.Order_Status_1.equals(o.getStatus())) {
                // 计算骑行时间差
                Long seconds = Long.parseLong(o.getTripTime());
                orderResponse.setTripTimeDesc(BikeSystemUtil.getTripDistance(seconds));
                // 结束使用的订单才展示
                responseList.add(orderResponse);
            }
            if(Constans.ORDER_STATUS.Order_Status_0.equals(o.getStatus())){
                orderResponse.setStatusDesc(Constans.ORDER_STATUS.Order_Status_0_DESC);
            }else if(Constans.ORDER_STATUS.Order_Status_1.equals(o.getStatus())){
                orderResponse.setStatusDesc(Constans.ORDER_STATUS.Order_Status_1_DESC);
            }
        }
        result.setData(responseList);
        return result;
    }

    @RequestMapping(value = "/deleteOrderInfoByOrderId", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteOrderInfoByOrderId(HttpServletRequest request){
        Result result = new Result();
        String orderId = request.getParameter("orderId");
        int num = orderService.deleteOrderInfoByOrderId(orderId);
        return result;
    }

    /**
     * 新增订单
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveBikeOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result openTheBike(@RequestBody JSONObject request) throws Exception{
        Result result = new Result();
        JSONObject json = JSON.parseObject(request.toJSONString());
        String bikeId = json.getString("bikeId");
        String userId = json.getString("userId");
        String longitudeStart = json.getString("longitudeStart");
        String latitudeStart = json.getString("latitudeStart");
        // 车辆状态更新为“租用”
        Bike bike = bikeService.getBikeInfoByBikeID(bikeId);
        bike.setBikeStatus(Constans.BIKE_STATUS.Bike_Status_2);
        bikeService.updateBikeStatus(bike);
        // 查询站点信息
        Site site = siteService.querySiteBySiteId(bike.getSiteId());
        // 新增订单
        Order newOrder = new Order();
        newOrder.setBikeId(bikeId);
        newOrder.setUserId(Integer.parseInt(userId));
        // 获取UUID的最后12位
        newOrder.setOrderId(UUID.randomUUID().toString().substring(24));
        newOrder.setStartTime(new Date());
        newOrder.setStatus(Constans.ORDER_STATUS.Order_Status_0);
        newOrder.setLongitudeStart(longitudeStart);
        newOrder.setLatitudeStart(latitudeStart);
        orderService.saveBikeOrderInfo(newOrder);
        return result;
    }

    /**
     * 查询该用户是否有进行中的订单
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryUserOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result queryUserOrder(@RequestBody JSONObject request) throws Exception{
        Result result = new Result();
        boolean isExistOrder = false;
        String orderId = null;
        JSONObject json = JSON.parseObject(request.toJSONString());
        String userId = json.getString("userId");
        List<Order> orderList = orderService.getOrderByUserID(Long.parseLong(userId));
        for(int i = 0; i<orderList.size();i++){
            Order order = orderList.get(i);
            if(Constans.ORDER_STATUS.Order_Status_0.equals(order.getStatus())){
                isExistOrder = true;
                orderId = order.getOrderId();
                break;
            }
        }
        Map map = new HashMap();
        map.put("isExistOrder",isExistOrder);
        map.put("orderId",orderId);
        result.setData(map);
        return result;
    }

    @RequestMapping(value = "/queryOrderByOrderId",method = RequestMethod.POST)
    @ResponseBody
    public Result queryOrderByOrderId(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String orderId = jsonObject.getString("orderId");
        Order order = orderService.queryOrderByOrderId(orderId);
        order.setEndTime(new Date());
        // 计算骑行时间差
        Long seconds = (order.getEndTime().getTime() - order.getStartTime().getTime()) / 1000;
        order.setTripTime(seconds.toString());
        // 父类对象数值传给子类
        OrderResponse orderResponse = new OrderResponse();
        copyOrder(order,orderResponse);
        orderResponse.setTripTimeDesc(BikeSystemUtil.getTripDistance(seconds));
        // 计算费用
        Double price = Math.ceil(seconds / (halfHour * 60)) * perPrice;
        orderResponse.setRentalCost(price.toString());
        result.setData(orderResponse);
        return result;
    }

    /**
     * 完成订单--更新数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateBikeOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result updateBikeOrder(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String orderInfo = jsonObject.getString("orderInfo");
        String longitudeSEnd = jsonObject.getString("longitudeSEnd");
        String latitudeEnd = jsonObject.getString("latitudeEnd");
        Order order = JSONObject.parseObject(orderInfo,Order.class);
        order.setLongitudeEnd(longitudeSEnd);
        order.setLatitudeEnd(latitudeEnd);
        order.setEndTime(new Date());
        order.setStatus(Constans.ORDER_STATUS.Order_Status_1);
        orderService.updateBikeOrderInfo(order);
        // 账户余额扣除费用
        User user = userService.getUserByID(Long.valueOf(order.getUserId()));
        Double balance = Double.valueOf(user.getUserBalance());
        balance -= Double.valueOf(order.getRentalCost());
        user.setUserBalance(balance.toString());
        userService.updateUserBalance(user);
        // 费用详情表新增数据
        ExpenseDetail expenseDetail = new ExpenseDetail();
        expenseDetail.setUserId(user.getUserId());
        expenseDetail.setExpenseAmount("-" + order.getRentalCost());
        expenseDetail.setCostDesc(Constans.EXPENSE_TYPE.EXPENSE_TYPE_2);
        expenseDetail.setUsedTime(new Date());
        expenseDetailService.saveExpenseDetail(expenseDetail);
        // 更新车辆租用次数
        Bike bike = bikeService.getBikeInfoByBikeID(order.getBikeId());
        Long rentalNum = bike.getRentalNum();
        rentalNum++;
        bike.setRentalNum(rentalNum);
        bikeService.updateRentalNum(bike);
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
        orderResponse.setTripTime(order.getTripTime());
        orderResponse.setRentalCost(order.getRentalCost());
        orderResponse.setLongitudeStart(order.getLongitudeStart());
        orderResponse.setLatitudeStart(order.getLatitudeStart());
        orderResponse.setLongitudeEnd(order.getLongitudeEnd());
        orderResponse.setLatitudeEnd(order.getLatitudeEnd());
        orderResponse.setBikeId(order.getBikeId());
    }

}
