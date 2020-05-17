package com.xmut.service.repair;

import com.xmut.dao.RepairDao;
import com.xmut.pojo.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService{
    @Autowired
    private RepairDao repairDao;

    @Override
    public void saveRepairInfo(Repair repair) {
        repairDao.saveRepairInfo(repair);
    }

    @Override
    public List<Repair> getRepairInfoByRepairStatus(Repair repair) {
        return repairDao.getRepairInfoByRepairStatus(repair);
    }

    @Override
    public List<Repair> getRepairInfoByBikeId(String bikeId) {
        return repairDao.getRepairInfoByBikeId(bikeId);
    }

    @Override
    public int deleteRepairInfoByRepairId(String repairId) {
        return repairDao.deleteRepairInfoByRepairId(repairId);
    }

    @Override
    public void updateRepairInfo(Repair repair) {
        repairDao.updateRepairInfo(repair);
    }
}
