package com.xmut.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Repair implements Serializable {
    private static final long serialVersionUID = 8959353883461643847L;

    private Long id;
    /**
     * 维修单号
     */
    private String repairId;
    /**
     * 车辆编号
     */
    private String bikeId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 维修内容
     */
    private String repairContent;
    /**
     * 维修时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date repairTime;
    /**
     * 维修结果（0. 投入使用 1.报废）
     */
    private String repairResult;
    /**
     * 维修状态（0. 待维修，1. 维修完成）
     */
    private String repairStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public String getRepairResult() {
        return repairResult;
    }

    public void setRepairResult(String repairResult) {
        this.repairResult = repairResult;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }
}
