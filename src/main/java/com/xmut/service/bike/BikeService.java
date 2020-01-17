package com.xmut.service.bike;

import com.xmut.pojo.Bike;

import java.util.List;

public interface BikeService {
    /**
     * 查询所有自行车信息
     * @return
     */
    List<Bike> getAllBikeInfo();
}
