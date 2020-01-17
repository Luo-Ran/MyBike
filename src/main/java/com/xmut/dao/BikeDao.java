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
}
