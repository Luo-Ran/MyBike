package com.xmut.controller;

import com.xmut.common.Constans;
import com.xmut.common.Result;
import com.xmut.pojo.Bike;
import com.xmut.service.bike.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    private BikeService bikeService;

    @RequestMapping(value = "/getAllBikeInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllBikeInfo(){
        Result result = new Result();
        List<Bike> bikeList = bikeService.getAllBikeInfo();
        for(int i=0;i < bikeList.size();i++){
            // 自行车类型字段翻译
            Bike bike = bikeList.get(i);
            if(Constans.BIKE_TYPE.MOUNTAIN_BIKE.equals(bike.getBikeType())){
                bikeList.get(i).setBikeTypeDesc(Constans.BIKE_TYPE.MOUNTAIN_BIKE_DESC);
            }else if(Constans.BIKE_TYPE.FLAT_BIKE.equals(bike.getBikeType())){
                bikeList.get(i).setBikeTypeDesc(Constans.BIKE_TYPE.FLAT_BIKE_DESC);
            }
        }
        result.setData(bikeList);
        return result;
    }
}
