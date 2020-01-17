package com.xmut.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Administrator
 */
public class Bike implements Serializable {
    private static final long serialVersionUID = -6657980991093874493L;
    private Long bikeId;
    /**
     * 自行车品牌
     */
    private String brand;
    /**
     * 自行车型号
     */
    private String bikeModel;
    /**
     * 自行车数量
     */
    private Long bikeCount;
    /**
     * 生产日期
     */
    private Date productionDate;
    /**
     * 采购日期
     */
    private Date procurementDate;
    /**
     * 已出租数量
     */
    private Long rentedCount;

    private String bikeImg;

    private String bikeType;

    private String bikeTypeDesc;

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getProcurementDate() {
        return procurementDate;
    }

    public void setProcurementDate(Timestamp procurementDate) {
        this.procurementDate = procurementDate;
    }


    public void setProcurementDate(Date procurementDate) {
        this.procurementDate = procurementDate;
    }

    public String getBikeImg() {
        return bikeImg;
    }

    public void setBikeImg(String bikeImg) {
        this.bikeImg = bikeImg;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public String getBikeTypeDesc() {
        return bikeTypeDesc;
    }

    public void setBikeTypeDesc(String bikeTypeDesc) {
        this.bikeTypeDesc = bikeTypeDesc;
    }

    public Long getBikeCount() {
        return bikeCount;
    }

    public void setBikeCount(Long bikeCount) {
        this.bikeCount = bikeCount;
    }

    public Long getRentedCount() {
        return rentedCount;
    }

    public void setRentedCount(Long rentedCount) {
        this.rentedCount = rentedCount;
    }
}
