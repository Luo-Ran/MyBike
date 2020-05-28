package com.xmut.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public Result saveRepairInfo(@RequestBody JSONObject request) throws ParseException {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("报修成功");
        JSONObject json = JSON.parseObject(request.toJSONString());
        String bikeId = json.getString("bikeId");
        String repairType = json.getString("repairType");
        String problemDesc  = json.getString("problemDesc");
        String userId = json.getString("userId");
        // 查询车辆
        Bike bike = bikeService.getBikeInfoByBikeID(bikeId);
        if(Constans.BIKE_STATUS.Bike_Status_2.equals(bike.getBikeStatus())){
            result.setSuccess(false);
            result.setMessage("车辆已被租用，无法报修");
        }else if(Constans.BIKE_STATUS.Bike_Status_3.equals(bike.getBikeStatus())){
            // 车辆已报修，无需操作，直接返回
        }else{
            // 修改自行车状态
            bike.setBikeStatus(Constans.BIKE_STATUS.Bike_Status_3);
            bikeService.updateBikeStatus(bike);
            // 保存维修信息
            Repair repair = new Repair();
            // 根据时间生成ID
            String time= Integer.toHexString((int)new Date().getTime());
            repair.setRepairId("REP"+ time.toUpperCase());
            repair.setBikeId(bikeId);
            repair.setSiteId(bike.getSiteId());
            repair.setUserId(Long.parseLong(userId));
            // 维修内容
            StringBuilder repairContent = new StringBuilder();
            switch (repairType){
                case "1": repairContent.append(Constans.REPAIR_PROBLEM.REPAIR_PROBLEM_1_DESC);break;
                case "2": repairContent.append(Constans.REPAIR_PROBLEM.REPAIR_PROBLEM_2_DESC);break;
                case "3": repairContent.append(Constans.REPAIR_PROBLEM.REPAIR_PROBLEM_3_DESC);break;
                case "4": repairContent.append(Constans.REPAIR_PROBLEM.REPAIR_PROBLEM_4_DESC);break;
                case "5": repairContent.append(Constans.REPAIR_PROBLEM.REPAIR_PROBLEM_5_DESC);break;
                case "6": repairContent.append(Constans.REPAIR_PROBLEM.REPAIR_PROBLEM_6_DESC);break;
                case "7": repairContent.append(Constans.REPAIR_PROBLEM.REPAIR_PROBLEM_7_DESC);break;
            }
            if (problemDesc != null && problemDesc.length() != 0){
                repairContent.append("-");
                repairContent.append(problemDesc);
            }
            repair.setRepairContent(repairContent.toString());
            repair.setRepairTime(new Date());
            repair.setRepairStatus(Constans.REPAIR_STUATUS.REPAIR_STUATUS_0);
            repairService.saveRepairInfo(repair);
        }
        return result;
    }

    /**
     * 查询维修信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getRepairInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result getRepairInfo(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String repairStatus = jsonObject.getString("repairStatus");
        String repairValue = jsonObject.getString("repairValue");
        List<RepairResponse> repairResponseListList = new ArrayList<>();
        // 查询维修信息
        Repair r = new Repair();
        r.setRepairStatus(repairStatus);
        if(StringUtils.isEmpty(repairValue)){
            repairValue = "B";
        }
        r.setBikeId(repairValue);
        List<Repair> repairList = repairService.getRepairInfoByRepairStatus(r);
        // 维修信息处理
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
        if(1 != num){
            result.setSuccess(false);
        }
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
