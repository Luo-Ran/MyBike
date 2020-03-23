package com.xmut.service.expenseDetail;

import com.xmut.dao.ExpenseDetailDao;
import com.xmut.pojo.ExpenseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseDetailServiceImpl implements ExpenseDetailService {
    @Autowired
    private ExpenseDetailDao expenseDetailDao;
    @Override
    public List<ExpenseDetail> getExpenseDeatailByUserId(Long userId) {
        return expenseDetailDao.getExpenseDeatailByUserId(userId);
    }
}
