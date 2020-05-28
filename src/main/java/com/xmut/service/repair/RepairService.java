package com.xmut.service.repair;

import com.xmut.pojo.Repair;

import java.util.List;

public interface RepairService {

    /**
     * 批量保存维修数据
     * @param repair
     */
    void saveRepairInfo(Repair repair);

    /**
     * 模糊搜索维修信息
     * @param repair
     * @return
     */
    List<Repair> getRepairInfoByRepairStatus(Repair repair);

    /**
     * 根据车辆ID查询维修单
     * @param bikeId
     * @return
     */
    List<Repair> getRepairInfoByBikeId(String bikeId);

    /**
     * 根据repairId删除数据
     * @param repairId
     * @return
     */
    int deleteRepairInfoByRepairId(String repairId);

    /**
     * 维修完成-更新维修单
     * @param repair
     */
    void updateRepairInfo(Repair repair);

    /**
     * 根据维修时间查询维修单
     * @param repairTime
     * @param siteId
     * @return
     */
    int countRepairByTime(String repairTime, Long siteId);
}
