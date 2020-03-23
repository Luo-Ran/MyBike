package com.xmut.controller;

import com.alibaba.fastjson.JSON;
import com.xmut.common.Result;
import com.xmut.pojo.ExpenseDetail;
import com.xmut.pojo.User;
import com.xmut.service.expenseDetail.ExpenseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/expenseDetail")
public class ExpenseDetailController {
    @Autowired
    private ExpenseDetailService expenseDetailService;

    @RequestMapping(value = "/getExpenseDeatailByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Result getExpenseDeatailByUserId(HttpServletRequest request){
        Result result = new Result();
        String user = request.getParameter("user");
        User u = JSON.parseObject(user, User.class);
        List<ExpenseDetail> expenseDetailList = expenseDetailService.getExpenseDeatailByUserId(u.getUserId());
        result.setData(expenseDetailList);
        return result;
    }
}
