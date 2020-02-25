package com.xmut.dao;

import com.xmut.pojo.Bike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Administrator
 */
@Mapper
@Repository
public interface BikeDao {
    /**
     * 查询所有自行车信息
     * @return
     */
    List<Bike> getAllBikeInfo();

    /**
     * 根据状态查询自信车
     * @param bikeStatus
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
}
