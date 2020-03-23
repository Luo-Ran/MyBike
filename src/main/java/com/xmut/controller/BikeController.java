package com.xmut.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmut.common.Constans;
import com.xmut.common.Result;
import com.xmut.common.ResultType;
import com.xmut.pojo.Bike;
import com.xmut.service.bike.BikeService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    private BikeService bikeService;

    /**
     * 获取全部自行车信息
     * @return
     */
    @RequestMapping(value = "/getAllBikeInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllBikeInfo() {
        Result result = new Result();
        List<Bike> bikeList = bikeService.getAllBikeInfo();
        // 自行车状态（0.已登记、1.待借、2.租用、3.维修、4.挂失、5.报废）
        List<Bike> bike0 = new ArrayList<>();
        List<Bike> bike1 = new ArrayList<>();
        List<Bike> bike2 = new ArrayList<>();
        List<Bike> bike3 = new ArrayList<>();
        List<Bike> bike4 = new ArrayList<>();
        List<Bike> bike5 = new ArrayList<>();
        for (int i = 0; i < bikeList.size(); i++) {
            Bike bike = new Bike();
            bike = bikeList.get(i);
            if (bike.getBikeStatus().equals(Constans.BIKE_STATUS.Bike_Status_0)) {
                bike0.add(bike);
            } else if (bike.getBikeStatus().equals(Constans.BIKE_STATUS.Bike_Status_1)) {
                bike1.add(bike);
            } else if (bike.getBikeStatus().equals(Constans.BIKE_STATUS.Bike_Status_2)) {
                bike2.add(bike);
            } else if (bike.getBikeStatus().equals(Constans.BIKE_STATUS.Bike_Status_3)) {
                bike3.add(bike);
            } else if (bike.getBikeStatus().equals(Constans.BIKE_STATUS.Bike_Status_4)) {
                bike4.add(bike);
            } else {
                bike5.add(bike);
            }
        }
        Map<String, List<Bike>> resultMap = new HashMap<>();
        resultMap.put("bike0", bike0);
        resultMap.put("bike1", bike1);
        resultMap.put("bike2", bike2);
        resultMap.put("bike3", bike3);
        resultMap.put("bike4", bike4);
        resultMap.put("bike5", bike5);
        result.setData(resultMap);
        return result;
    }

    /**
     * 根据自行车状态获取自行车信息
     * @param bikeStatus
     * @return
     */
    @RequestMapping(value = "/getBikeInfoByStatus", method = RequestMethod.POST)
    @ResponseBody
    public Result getBikeInfoByStatus(String bikeStatus) {
        Result result = new Result();
        List<Bike> bikeList = bikeService.getBikeInfoByStatus(bikeStatus);
        result.setData(bikeList);
        return result;
    }

    /**
     * 自行车状态修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/changeBikeStatus", method = RequestMethod.POST )
    @ResponseBody
    public Result changeBikeStatus(@RequestBody JSONObject request){
        JSONObject json = JSON.parseObject(request.toJSONString());
        String bikes = json.getString("bikes");
        String targetStatus = json.getString("targetStatus");
        List<Bike> bikeList = JSONArray.parseArray(bikes,Bike.class);
        // 修改自行车状态
        for(Bike b:bikeList){
            b.setBikeStatus(targetStatus);
            bikeService.updateBikeStatus(b);
        }
        Result result = new Result();
        return result;
    }

    /**
     * 批量删除自行车
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteBikes", method = RequestMethod.POST )
    @ResponseBody
    public Result deleteBikes(HttpServletRequest request){
        Result result = new Result();
        String bikes = request.getParameter("bikes");
        List<Bike> bikeList = JSONArray.parseArray(bikes,Bike.class);
        // 获得bikeId
        String[] ids = new String[bikeList.size()];
        for(int i = 0;i < bikeList.size();i++){
            Bike b = bikeList.get(i);
            ids[i] = b.getBikeId();
        }
        int num = bikeService.batchDeleteBike(ids);
        if(bikeList.size() != num){
            result.setMessage(ResultType.FAIL.getName());
        }
        return result;
    }

    @RequestMapping(value = "/saveNewBike", method = RequestMethod.POST )
    @ResponseBody
    public Result saveNewBike(@RequestBody JSONObject request) throws Exception {
        Result result = new Result();
        JSONObject json = JSON.parseObject(request.toJSONString());
        String bikeType = json.getString("bikeType");
        String bikeNum = json.getString("bikeNum");
        int num = Integer.valueOf(bikeNum);
        String date = json.getString("date");
        String imgFileBase = json.getString("imgFileBase");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        List<Bike> bikeList = new ArrayList<>();
        for(int i=0;i < num;i++){
            Bike bike = new Bike();
            bike.setBikeId("B-" + System.currentTimeMillis());
            bike.setBikeType(bikeType);
            bike.setBikeStatus(Constans.BIKE_STATUS.Bike_Status_0);
            if(!StringUtils.isEmpty(date)){
                bike.setProcurementTime(sf.parse(date));
            }
            bike.setRentalNum(0L);
            bike.setImageUrl(imgFileBase);
            bikeList.add(bike);
        }
        bikeService.saveBikeInfo(bikeList);
        return result;
    }

}
