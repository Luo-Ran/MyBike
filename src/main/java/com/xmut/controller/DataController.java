package com.xmut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmut.common.BikeSystemUtil;
import com.xmut.common.Result;
import com.xmut.pojo.Order;
import com.xmut.service.order.OrderService;
import com.xmut.service.repair.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RepairService repairService;
    /**
     * 车辆租用情况
     * @param request
     * @return
     */
    @RequestMapping(value = "/rentalData",method = RequestMethod.POST)
    @ResponseBody
    public Result rentalData(@RequestBody JSONObject request) {
        Result result = new Result();
        // 查询该站点的车辆订单
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        Long siteId = jsonObject.getLong("siteId");
        Map resultMap = new HashMap();
        List<String> time = new ArrayList();
        List<Integer> number = new ArrayList();
        int j = 0;
        // 获取过去7天的车辆订单
        for(int i = 6; i >= 0; i--){
            try {
                String pastDate = BikeSystemUtil.getPastDate(i);
                int num = orderService.countBikeOrderByTime(pastDate,siteId);
                time.add(pastDate.substring(5,pastDate.length()));
                number.add(num);
            }catch (Exception e) {
                System.out.println(e);
                result.setSuccess(false);
            }
        }
        resultMap.put("rentalTime", time);
        resultMap.put("rentalNumber",number);
        result.setData(resultMap);
        return result;
    }

    /**
     * 维修数据统计
     * @param request
     * @return
     */
    @RequestMapping(value = "/repairData",method = RequestMethod.POST)
    @ResponseBody
    public Result repairData(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        Long siteId = jsonObject.getLong("siteId");
        Map resultMap = new HashMap();
        List<String> time = new ArrayList();
        List<Integer> number = new ArrayList();
        int j = 0;
        // 过去7天该站点的维修单
        for(int i = 6; i >= 0; i--){
            try {
                String pastDate = BikeSystemUtil.getPastDate(i);
                int num = repairService.countRepairByTime(pastDate,siteId);
                time.add(pastDate.substring(5,pastDate.length()));
                number.add(num);
            }catch (Exception e) {
                System.out.println(e);
                result.setSuccess(false);
            }
        }
        resultMap.put("repairTime", time);
        resultMap.put("repairNumber",number);
        result.setData(resultMap);
        return result;
    }

    /**
     * 利润信息统计
     * @param request
     * @return
     */
    @RequestMapping(value = "/profitData",method = RequestMethod.POST)
    @ResponseBody
    public Result profitData(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        Long siteId = jsonObject.getLong("siteId");
        Map resultMap = new HashMap();
        List<String> time = new ArrayList();
        List<Double> number = new ArrayList();
        int j = 0;
        // 过去7天该站点的租用订单
        for(int i = 6; i >= 0; i--){
            try {
                String pastDate = BikeSystemUtil.getPastDate(i);
                List<Order> orderList = orderService.orderProfitByTime(pastDate,siteId);
                Double num = 0.0;
                for(Order o:orderList){
                    num += Double.parseDouble(o.getRentalCost());
                }
                time.add(pastDate.substring(5,pastDate.length()));
                number.add(num);
            }catch (Exception e) {
                System.out.println(e);
                result.setSuccess(false);
            }
        }
        resultMap.put("profitTime", time);
        resultMap.put("profitNumber",number);
        result.setData(resultMap);
        return result;
    }

}
