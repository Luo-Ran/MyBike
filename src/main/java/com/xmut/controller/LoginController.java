package com.xmut.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmut.common.Constans;
import com.xmut.common.Result;
import com.xmut.pojo.Order;
import com.xmut.pojo.Params.AccountInfoResponse;
import com.xmut.pojo.Params.OrderResponse;
import com.xmut.pojo.User;
import com.xmut.service.order.OrderService;
import com.xmut.service.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/sysUser")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    public User user = null;
    private String balance = "0.00";

    /**
     *  登录验证
     * @param userNo
     * @param userPass
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Result login(String userNo, String userPass, HttpServletRequest request) {
        Result result = new Result();
        user = userService.checkUserLogin(userNo, userPass);
        if (user != null) {
            result.setData(user);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user.getUserId());
        } else {
            result.fail("用户名或密码错误!");
        }
        return result;
    }

    /**
     *  注销
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Result logout(HttpServletRequest request){
        Result result = new Result();
        request.getSession().removeAttribute("user");
        return result;
    }

    /**
     * 注册账号
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(HttpServletRequest request){
        Result result = new Result();
        String userName = request.getParameter("userName");
        String userNo = request.getParameter("userNo");
        String userPass = request.getParameter("userPass");
        User user = new User();
        user.setUserName(userName);
        user.setUserNo(userNo);
        user.setUserPass(userPass);
        user.setAccountAuthority(Constans.USER_TYPE.USER);
        user.setUserSign("这个人什么都没写");
        user.setUserBalance(balance);
        user.setAccountStatus(Constans.ACCOUNT_STATUS.USING);
        user.setUserSex(Constans.USER_SEX.MEN);
        user.setBirthday("2000-01-01");
        userService.insertUser(user);
        return result;
    }

    /**
     * 查询全部账号
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryAllUser", method = RequestMethod.POST)
    @ResponseBody
    public Result queryAllUser(HttpServletRequest request){
        Result result = new Result();
        List<User> userList = userService.queryAllUser();
        for(int i = 0;i<userList.size();i++){
            User user = userList.get(i);
            if(Constans.ACCOUNT_STATUS.USING.equals(user.getAccountStatus())){
                userList.get(i).setAccountStatusDesc(Constans.ACCOUNT_STATUS.USING_DESC);
            }else if(Constans.ACCOUNT_STATUS.PROHIBIT.equals(user.getAccountStatus())){
                userList.get(i).setAccountStatusDesc(Constans.ACCOUNT_STATUS.PROHIBIT_DESC);
            }
        }
        result.setData(userList);
        return result;
    }

    /**
     * 账号禁用
     * @param request
     * @return
     */
    @RequestMapping(value = "/changeAccountStatus", method = RequestMethod.POST)
    @ResponseBody
    public Result changeAccountStatus(HttpServletRequest request){
        Result result = new Result();
        Long userId = Long.valueOf(request.getParameter("userId"));
        String targetStatus = request.getParameter("targetStatus");
        User user = new User();
        user.setUserId(userId);
        user.setAccountStatus(targetStatus);
        int num = userService.updateAccountStatus(user);
        if(num != 1){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 账号资料
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result queryUserInfo(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject json = JSON.parseObject(request.toJSONString());
        Long userId = Long.valueOf(json.getString("userId"));
        User user = userService.getUserByID(userId);
        List<Order> orderList = orderService.getOrderByUserID(userId);
        Long seconds = 0L;
        Double tripDistance = 0d;
        for(Order o:orderList) {
            // 使用结束
            if (Constans.ORDER_STATUS.Order_Status_1.equals(o.getStatus())) {
                // 计算骑行时间   骑行距离
                seconds += (o.getEndTime().getTime() - o.getStartTime().getTime()) / 1000;
                tripDistance += Double.valueOf(o.getTripDist());
            }
        }
        Long second =  seconds % 60;
        Long minutes = (seconds - second) / 60;
        Long mintue = minutes % 60;
        Long hour = (minutes - mintue) / 60;
        StringBuffer tripTimeDesc = new StringBuffer();
        if(0 != hour){
            tripTimeDesc.append(hour);
            tripTimeDesc.append("小时");
        }
        if(0 != mintue){
            tripTimeDesc.append(mintue);
            tripTimeDesc.append("分钟");
        }
        if(0 != second){
            tripTimeDesc.append(second);
            tripTimeDesc.append("秒");
        }
        AccountInfoResponse accountInfoResponse = new AccountInfoResponse();
        BeanUtils.copyProperties(user,accountInfoResponse);
        accountInfoResponse.setTripDistance(tripDistance.toString());
        accountInfoResponse.setTripTime(tripTimeDesc.toString());
        result.setData(accountInfoResponse);
        return result;
    }

}