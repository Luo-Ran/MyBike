package com.xmut.service.bike;

import com.xmut.pojo.Bike;

import java.util.List;

public interface BikeService {
    /**
     * 查询所有自行车信息
     * @return
     */
    List<Bike> getAllBikeInfo();

    /**
     * 根据状态查询自信车
     * @return
     */
    List<Bike> getBikeInfoByStatus(String bikeStatus);

    /**
     * 根据bikeID查询自信车
     * @param bikeId
     * @return
     */
    Bike getBikeInfoByBikeID(Long bikeId);

    /**
     * 根据bikeID更新自行车状态
     * @param bike
     */
    void updateBikeStatus(Bike bike);

    /**
     * 批量删除
     * @param bikeIds
     * @return
     */
    int batchDeleteBike(String[] bikeIds);
}
