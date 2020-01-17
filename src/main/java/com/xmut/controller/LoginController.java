package com.xmut.controller;

import com.xmut.common.Result;
import com.xmut.pojo.User;
import com.xmut.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/sysUser")
public class LoginController {
    @Autowired
    UserService userService;

    public User user = null;

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
            request.getSession().setAttribute("user", user);
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
    public Result getUserInfo(HttpServletRequest request){
        Result result = new Result();
        request.getSession().removeAttribute("user");
        return result;
    }
}