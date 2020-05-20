package com.xmut.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmut.common.Result;
import com.xmut.pojo.Site;
import com.xmut.service.site.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private SiteService siteService;

    /**
     * 查询站点信息
     */
    @RequestMapping(value = "/queryAllSiteInfo", method = RequestMethod.POST )
    @ResponseBody
    public Result queryAllSiteInfo(){
        Result result = new Result();
        List<Site> siteList = new ArrayList<>();
        siteList = siteService.queryAllSiteInfo();
        result.setData(siteList);
        return result;
    }
    @RequestMapping(value = "/saveSite",method = RequestMethod.POST)
    @ResponseBody
    public Result saveSite(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String siteName = jsonObject.getString("siteName");
        String province = jsonObject.getString("province");
        String city = jsonObject.getString("city");
        String district = jsonObject.getString("district");
        String street = jsonObject.getString("street");
        String streetNumber = jsonObject.getString("streetNumber");
        String siteLongitude = jsonObject.getString("siteLongitude");
        String siteLatitude = jsonObject.getString("siteLatitude");
        Site site = new Site();
        if(!StringUtils.isEmpty(siteName)){
            site.setSiteName(siteName);
        }
        if(!StringUtils.isEmpty(province)){
            site.setSiteProvince(province);
        }
        if(!StringUtils.isEmpty(city)){
            site.setSiteCity(city);
        }
        if(!StringUtils.isEmpty(district)){
            site.setSiteDistrict(district);
        }
        if(!StringUtils.isEmpty(street)) {
            if (!StringUtils.isEmpty(streetNumber)) {
                street += streetNumber;
            }
            site.setSiteStreet(street);
        }
        if(!StringUtils.isEmpty(siteLongitude)){
            site.setSiteLongitude(siteLongitude);
        }
        if(!StringUtils.isEmpty(siteLatitude)){
            site.setSiteLatitude(siteLatitude);
        }
        site.setCreateTime(new Date());
        int num = siteService.saveSiteInfo(site);
        if( num != 1){
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/deleteSite",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteSite(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String longitude = jsonObject.getString("longitude");
        String latitude = jsonObject.getString("latitude");
        Site site = new Site();
        if (!StringUtils.isEmpty(longitude)) {
            site.setSiteLongitude(longitude);
        }
        if(!StringUtils.isEmpty(latitude)){
            site.setSiteLatitude(latitude);
        }
        int num = siteService.deleteSiteInfo(site);
        if(num != 1){
            result.setSuccess(false);
        }
        return result;
    }


}
