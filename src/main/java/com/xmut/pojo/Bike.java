package com.xmut.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Administrator
 */
public class Bike implements Serializable {
    private static final long serialVersionUID = -6657980991093874493L;
    private Long id;
    /**
     * 自行车编码
     */
    private String bikeId;
    /**
     * 自行车品牌
     */
    private String bikeType;
    /**
     * 自行车状态
     */
    private String bikeStatus;
    /**
     * 采购日期
     */
    private Date procurementTime;
    /**
     * 出租次数
     */
    private Long rentalNum;
    /**
     * 图片路径
     */
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public String getBikeStatus() {
        return bikeStatus;
    }

    public void setBikeStatus(String bikeStatus) {
        this.bikeStatus = bikeStatus;
    }

    public Date getProcurementTime() {
        return procurementTime;
    }

    public void setProcurementTime(Date procurementTime) {
        this.procurementTime = procurementTime;
    }

    public Long getRentalNum() {
        return rentalNum;
    }

    public void setRentalNum(Long rentalNum) {
        this.rentalNum = rentalNum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
