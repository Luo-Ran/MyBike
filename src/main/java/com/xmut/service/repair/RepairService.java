package com.xmut.service.repair;

import com.xmut.pojo.Repair;

import java.util.List;

public interface RepairService {

    /**
     * 批量保存维修数据
     * @param list
     */
    void saveRepairInfo(List<Repair> list);

    /**
     * 查询全部维修信息
     * @return
     */
    List<Repair> getAllRepairInfo();

    /**
     * 根据repairId删除数据
     * @param repairId
     * @return
     */
    int deleteRepairInfoByRepairId(String repairId);
}
