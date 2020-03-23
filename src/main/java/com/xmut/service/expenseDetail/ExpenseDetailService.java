package com.xmut.service.expenseDetail;

import com.xmut.pojo.ExpenseDetail;

import java.util.List;

public interface ExpenseDetailService {
    /**
     * 根据用户ID查询费用明细
     * @param userId
     * @return
     */
    List<ExpenseDetail> getExpenseDeatailByUserId(Long userId);
}
