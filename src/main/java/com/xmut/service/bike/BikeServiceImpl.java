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
}
