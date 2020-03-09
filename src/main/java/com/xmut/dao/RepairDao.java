package com.xmut.dao;

import com.xmut.pojo.Repair;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RepairDao {

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
