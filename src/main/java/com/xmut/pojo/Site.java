package com.xmut.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Site implements Serializable {

    private static final long serialVersionUID = 6938035549749149594L;
    private Long id;
    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 站点经度
     */
    private String siteLongitude;
    /**
     * 站点纬度
     */
    private String siteLatitude;
    /**
     * 省份
     */
    private String siteProvince;
    /**
     * 城市
     */
    private String siteCity;
    /**
     * 区域
     */
    private String siteDistrict;
    /**
     * 街道
     */
    private String siteStreet;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteLongitude() {
        return siteLongitude;
    }

    public void setSiteLongitude(String siteLongitude) {
        this.siteLongitude = siteLongitude;
    }

    public String getSiteLatitude() {
        return siteLatitude;
    }

    public void setSiteLatitude(String siteLatitude) {
        this.siteLatitude = siteLatitude;
    }

    public String getSiteProvince() {
        return siteProvince;
    }

    public void setSiteProvince(String siteProvince) {
        this.siteProvince = siteProvince;
    }

    public String getSiteCity() {
        return siteCity;
    }

    public void setSiteCity(String siteCity) {
        this.siteCity = siteCity;
    }

    public String getSiteDistrict() {
        return siteDistrict;
    }

    public void setSiteDistrict(String siteDistrict) {
        this.siteDistrict = siteDistrict;
    }

    public String getSiteStreet() {
        return siteStreet;
    }

    public void setSiteStreet(String siteStreet) {
        this.siteStreet = siteStreet;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
