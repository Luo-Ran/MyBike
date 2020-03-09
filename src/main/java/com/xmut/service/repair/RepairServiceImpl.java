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
    public void saveRepairInfo(List<Repair> list) {
        repairDao.saveRepairInfo(list);
    }

    @Override
    public List<Repair> getAllRepairInfo() {
        return repairDao.getAllRepairInfo();
    }

    @Override
    public int deleteRepairInfoByRepairId(String repairId) {
        return repairDao.deleteRepairInfoByRepairId(repairId);
    }
}
