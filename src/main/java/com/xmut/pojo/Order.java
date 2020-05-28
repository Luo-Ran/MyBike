package com.xmut.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = 8689300631452757194L;

    private Long id;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 车辆编码
     */
    private String bikeId;
    /**
     * 站点ID
     */
    private Long siteId;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 开始租用时间
     */

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;
    /**
     * 结束租用时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;
    /**
     * 骑行时间
     */
    private String tripTime;
    /**
     * 骑行距离
     */
    private String tripDist;
    /**
     * 订单费用
     */
    private String rentalCost;
    /**
     * 起始位置经度
     */
    private String longitudeStart;
    /**
     * 起始位置纬度
     */
    private String latitudeStart;
    /**
     * 结束位置经度
     */
    private String longitudeEnd;
    /**
     * 结束位置纬度
     */
    private String latitudeEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTripDist() {
        return tripDist;
    }

    public void setTripDist(String tripDist) {
        this.tripDist = tripDist;
    }

    public String getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(String rentalCost) {
        this.rentalCost = rentalCost;
    }

    public String getLongitudeStart() {
        return longitudeStart;
    }

    public void setLongitudeStart(String longitudeStart) {
        this.longitudeStart = longitudeStart;
    }

    public String getLatitudeStart() {
        return latitudeStart;
    }

    public void setLatitudeStart(String latitudeStart) {
        this.latitudeStart = latitudeStart;
    }

    public String getLongitudeEnd() {
        return longitudeEnd;
    }

    public void setLongitudeEnd(String longitudeEnd) {
        this.longitudeEnd = longitudeEnd;
    }

    public String getLatitudeEnd() {
        return latitudeEnd;
    }

    public void setLatitudeEnd(String latitudeEnd) {
        this.latitudeEnd = latitudeEnd;
    }

    public String getTripTime() {
        return tripTime;
    }

    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
}
