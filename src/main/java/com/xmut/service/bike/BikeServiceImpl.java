package com.xmut.service.bike;

import com.xmut.dao.BikeDao;
import com.xmut.pojo.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class BikeServiceImpl implements BikeService{
    @Autowired
    private BikeDao bikeDao;

    @Override
    public List<Bike> getAllBikeInfo() {
        return bikeDao.getAllBikeInfo();
    }

    @Override
    public List<Bike> getBikeInfoByStatus(Bike bike) {
        return bikeDao.getBikeInfoByStatus(bike);
    }

    @Override
    public Bike getBikeInfoByBikeID(String bikeId) {
        return bikeDao.getBikeInfoByBikeID(bikeId);
    }

    @Override
    public List<Bike> getBikeInfoBySiteID(Long siteId) {
        return bikeDao.getBikeInfoBySiteID(siteId);
    }

    @Override
    public void updateBikeStatus(Bike bike) {
        bikeDao.updateBikeStatus(bike);
    }

    @Override
    public int batchDeleteBike(String[] bikeIds) {
        return bikeDao.batchDeleteBike(bikeIds);
    }

    @Override
    public void saveBikeInfo(List<Bike> bikeList) {
        bikeDao.saveBikeInfo(bikeList);
    }

    @Override
    public void updateRentalNum(Bike bike) {
        bikeDao.updateRentalNum(bike);
    }
}
