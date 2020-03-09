package com.xmut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xmut.common.Constans;
import com.xmut.common.Result;
import com.xmut.pojo.Bike;
import com.xmut.pojo.Params.RepairResponse;
import com.xmut.pojo.Repair;
import com.xmut.pojo.User;
import com.xmut.service.bike.BikeService;
import com.xmut.service.repair.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;
    @Autowired
    private BikeService bikeService;

    /**
     * 维修信息保存
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveRepairInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result saveRepairInfo(HttpServletRequest request){
        Result result = new Result();
        String bikes = request.getParameter("bikes");
        String repairStatus = request.getParameter("repairStatus");
        String user  = request.getParameter("user");
        String repairContent = request.getParameter("repairContent");
        List<Bike> bikeList = JSONArray.parseArray(bikes,Bike.class);
        User u = JSON.parseObject(user,User.class);
        List<Repair> repairList = new ArrayList<>();
        for(Bike bike:bikeList){
            Repair repair = new Repair();
            repair.setRepairId("REP" + System.currentTimeMillis());
            repair.setBikeId(bike.getBikeId());
            repair.setUserId(u.getUserId());
            repair.setRepairContent(repairContent);
            repair.setRepairTime(new Date(System.currentTimeMillis()));
            repair.setRepairResult(repairStatus);
            repairList.add(repair);

            // 修改自行车状态
            if(Constans.REPAIR_RESULT.REPAIR_RESULT_0.equals(repairStatus)){
                bike.setBikeStatus(Constans.BIKE_STATUS.Bike_Status_1);
            }else if(Constans.REPAIR_RESULT.REPAIR_RESULT_1.equals(repairStatus)){
                bike.setBikeStatus(Constans.BIKE_STATUS.Bike_Status_5);
            }
            bikeService.updateBikeStatus(bike);
        }
        repairService.saveRepairInfo(repairList);
        return result;
    }

    /**
     * 查询维修信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getRepairInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result getRepairInfo(HttpServletRequest request){
        Result result = new Result();
        List<RepairResponse> repairResponseListList = new ArrayList<>();
        List<Repair> repairList = repairService.getAllRepairInfo();
        for(Repair repair:repairList){
            RepairResponse rep = new RepairResponse();
            copyToResponse(repair,rep);
            if(Constans.REPAIR_RESULT.REPAIR_RESULT_0.equals(repair.getRepairResult())){
                rep.setRepairResultDesc(Constans.REPAIR_RESULT.REPAIR_RESULT_0_DESC);
            }else if(Constans.REPAIR_RESULT.REPAIR_RESULT_1.equals(repair.getRepairResult())){
                rep.setRepairResultDesc(Constans.REPAIR_RESULT.REPAIR_RESULT_1_DESC);
            }
            repairResponseListList.add(rep);
        }
        result.setData(repairResponseListList);
        return result;
    }

    /**
     * 删除信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteRepairInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteRepairInfo(HttpServletRequest request){
        Result result = new Result();
        String repairId = request.getParameter("repairId");
        int num = repairService.deleteRepairInfoByRepairId(repairId);
        return result;
    }

    private void copyToResponse(Repair repair, RepairResponse repairResponse){
        repairResponse.setId(repair.getId());
        repairResponse.setRepairId(repair.getRepairId());
        repairResponse.setBikeId(repair.getBikeId());
        repairResponse.setUserId(repair.getUserId());
        repairResponse.setRepairContent(repair.getRepairContent());
        repairResponse.setRepairResult(repair.getRepairResult());
        repairResponse.setRepairTime(repair.getRepairTime());
    }
}
