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
}
