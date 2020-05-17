package com.xmut.controller;

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
}
