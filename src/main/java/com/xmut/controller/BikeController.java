package com.xmut.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmut.common.BikeSystemUtil;
import com.xmut.common.Constans;
import com.xmut.common.Result;
import com.xmut.common.ResultType;
import com.xmut.pojo.Bike;
import com.xmut.pojo.Repair;
import com.xmut.pojo.Site;
import com.xmut.service.bike.BikeService;
import com.alibaba.fastjson.JSONArray;
import com.xmut.service.repair.RepairService;
import com.xmut.service.site.SiteService;
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
    @Autowired
    private SiteService siteService;
    @Autowired
    private RepairService repairService;

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
     * @param request
     * @return
     */
    @RequestMapping(value = "/getBikeInfoByStatus", method = RequestMethod.POST)
    @ResponseBody
    public Result getBikeInfoByStatus(@RequestBody JSONObject request) {
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String bikeStatus = jsonObject.getString("bikeStatus");
        String siteId = jsonObject.getString("siteId");
        String searchValue = jsonObject.getString("searchValue");
        // 车辆查询
        Bike bike = new Bike();
        bike.setBikeStatus(bikeStatus);
        if(StringUtils.isEmpty(searchValue)){
           searchValue = "B";
        }
        bike.setBikeId(searchValue);
        List<Bike> bikeList = bikeService.getBikeInfoByStatus(bike);
        // 站点查询
        List<Site> siteList = siteService.queryAllSiteInfo();
        List<Bike> resList = new ArrayList<>();
        Long sid = Long.valueOf(siteId);
        // 站点名称翻译
        for(int i = 0; i < bikeList.size(); i++){
            // 根据站点过滤数据（0代表全部站点）
            if(sid == 0 || sid == bikeList.get(i).getSiteId()){
                for(int j = 0;j < siteList.size(); j++){
                    if(siteList.get(j).getId().equals(bikeList.get(i).getSiteId())){
                        bikeList.get(i).setSiteName(siteList.get(j).getSiteName());
                        resList.add(bikeList.get(i));
                        continue;
                    }
                }
            }
        }
        result.setData(resList);
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
        for(Bike b:bikeList){
            // 如果当前车辆状态为“维修状态”，更新repair表
            if(Constans.BIKE_STATUS.Bike_Status_3.equals(b.getBikeStatus())){
                List<Repair> repairList = repairService.getRepairInfoByBikeId(b.getBikeId());
                for(int i=0;i<repairList.size();i++){
                    Repair repair = repairList.get(i);
                    repair.setRepairTime(new Date());
                    repair.setRepairStatus(Constans.REPAIR_STUATUS.REPAIR_STUATUS_1);
                    // 投入使用
                    if(Constans.BIKE_STATUS.Bike_Status_1.equals(targetStatus)){
                        repair.setRepairResult(Constans.REPAIR_RESULT.REPAIR_RESULT_0);
                    }else { // 报废
                        repair.setRepairResult(Constans.REPAIR_RESULT.REPAIR_RESULT_1);
                    }
                    repairService.updateRepairInfo(repair);
                }
            }
            // 修改自行车状态
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

    /**
     * 添加车辆
     * @param request
     * @return
     * @throws Exception
     */
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
        Long siteId = json.getLong("siteId");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        List<Bike> bikeList = new ArrayList<>();
        for(int i=0;i < num;i++){
            Bike bike = new Bike();
            bike.setBikeId("B-" + UUID.randomUUID().toString().substring(24).toUpperCase());
            bike.setBikeType(bikeType);
            bike.setBikeStatus(Constans.BIKE_STATUS.Bike_Status_1);
            if(!StringUtils.isEmpty(date)){
                bike.setProcurementTime(sf.parse(date));
            }
            bike.setRentalNum(0L);
            if(!StringUtils.isEmpty(imgFileBase)){
                bike.setImageUrl(imgFileBase);// 使用上传的图片
            }else{// 设置默认图片
                String c = System.getProperty("user.dir");// 获取项目项目相对路径
                String img ="data:image/jpeg;base64,/";
                img = img + BikeSystemUtil.getImageBase64(c+"\\src\\main\\resources\\image\\defaultImg.jpg");
                bike.setImageUrl(img);
            }
            bike.setSiteId(siteId);
            bikeList.add(bike);
        }
        bikeService.saveBikeInfo(bikeList);
        return result;
    }

    @RequestMapping(value = "/getBikeInfoByBikeID",method = RequestMethod.POST)
    @ResponseBody
    public Result getBikeInfoByBikeID(@RequestBody JSONObject request) throws Exception{
        Result result = new Result();
        JSONObject json = JSON.parseObject(request.toJSONString());
        String bikeId = json.getString("bikeId");
        Bike bike = bikeService.getBikeInfoByBikeID(bikeId);
        List<Site> siteList = siteService.queryAllSiteInfo();
        for(int i=0;i<siteList.size();i++){
            if(siteList.get(i).getId().equals(bike.getSiteId())){
                bike.setSiteName(siteList.get(i).getSiteName());
                break;
            }
        }
        result.setData(bike);
        return result;
    }

}
