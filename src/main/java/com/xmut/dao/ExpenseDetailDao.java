package com.xmut.dao;

import com.xmut.pojo.ExpenseDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExpenseDetailDao {
    /**
     * 根据用户ID查询费用明细
     * @param userId
     * @return
     */
    List<ExpenseDetail> getExpenseDeatailByUserId(Long userId);
}
