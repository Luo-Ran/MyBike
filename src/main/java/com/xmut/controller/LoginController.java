package com.xmut.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmut.common.BikeSystemUtil;
import com.xmut.common.Constans;
import com.xmut.common.Result;
import com.xmut.common.ResultType;
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
import sun.org.mozilla.javascript.internal.json.JsonParser;

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
    private Double oneKm = 1000.0;

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
        // 默认头像
        String c = System.getProperty("user.dir");// 获取项目项目相对路径
        String headImg ="data:image/jpeg;base64,";
        headImg = headImg + BikeSystemUtil.getImageBase64(c+"\\src\\main\\resources\\image\\headImg.jpg");
        user.setHeadImg(headImg);
        userService.insertUser(user);
        return result;
    }

    /**
     * 查询账号
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    @ResponseBody
    public Result queryUser(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String authority = jsonObject.getString("authority");
        String userNoValue = jsonObject.getString("userNoValue");
        User searchUser = new User();
        searchUser.setAccountAuthority(authority);
        if(!StringUtils.isEmpty(userNoValue)){
            searchUser.setUserNo(userNoValue);
        }
        List<User> userList = userService.queryUserByUserNo(searchUser);
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

    @RequestMapping(value = "/queryAllUser", method = RequestMethod.POST)
    @ResponseBody
    public Result queryAllUser(){
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
    public Result changeAccountStatus(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        Long userId = Long.valueOf(jsonObject.getString("userId"));
        String targetStatus = jsonObject.getString("targetStatus");
        User user = new User();
        user.setUserId(userId);
        user.setAccountStatus(targetStatus);
        int num = userService.updateUserAccount(user);
        if(num != 1){
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/changeAccountAuthority", method = RequestMethod.POST)
    @ResponseBody
    public Result changeAccountAuthority(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String userId = jsonObject.getString("userId");
        String targetAuthority = jsonObject.getString("targetAuthority");
        User user = new User();
        user.setUserId(Long.parseLong(userId));
        user.setAccountAuthority(targetAuthority);
        int num = userService.updateUserAccount(user);
        if(1 != num){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    @ResponseBody
    public Result changePassword(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String newPassword = jsonObject.getString("newPassword");
        String oldPassword = jsonObject.getString("oldPassword");
        Long userId = jsonObject.getLong("userId");
        User user = userService.getUserByID(userId);
        if(oldPassword.equals(user.getUserPass())) {
            user.setUserPass(newPassword);
            int num = userService.updateUserPass(user);
            if (1 != num) {
                result.setSuccess(false);
                result.setMessage(ResultType.FAIL.getName());
            }
        }else {
            result.setSuccess(false);
            result.setMessage("旧密码错误");
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
                seconds += Long.parseLong(o.getTripTime());
                String distance = o.getTripDist();
                if("米".equals(distance.substring(distance.length()-1))){
                    distance = distance.substring(0,distance.length()-1);
                    tripDistance += Double.valueOf(distance) / oneKm;
                }else {
                    distance = distance.substring(0,distance.length()-2);
                    tripDistance += Double.valueOf(distance);
                }

            }
        }

        AccountInfoResponse accountInfoResponse = new AccountInfoResponse();
        BeanUtils.copyProperties(user,accountInfoResponse);
        accountInfoResponse.setTripDistance(tripDistance.toString());
        accountInfoResponse.setTripTime(BikeSystemUtil.getTripDistance(seconds));
        result.setData(accountInfoResponse);
        return result;
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result updateUserInfo(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String userObject = jsonObject.getString("user");
        User user = JSONObject.parseObject(userObject,User.class);
        userService.updateUserInfo(user);
        return  result;
    }

    @RequestMapping(value = "/deleteUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteUserInfo(@RequestBody JSONObject request){
        Result result = new Result();
        JSONObject jsonObject = JSON.parseObject(request.toJSONString());
        String userId = jsonObject.getString("userId");
        User user = new User();
        user.setUserId(Long.parseLong(userId));
        int num = userService.deleteUserByUserID(user);
        if(1 != num){
            result.setSuccess(false);
        }
        return result;
    }

}