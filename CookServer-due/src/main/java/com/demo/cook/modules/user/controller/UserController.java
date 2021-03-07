package com.demo.cook.modules.user.controller;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.user.model.Register;
import com.demo.cook.modules.user.model.User;
import com.demo.cook.modules.user.model.UserInfo;
import com.demo.cook.modules.user.service.IUserService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    IUserService userService;

    @Autowired
    public void setUserService(IUserService userService){
        this.userService=userService;
    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public RtnResult<UserInfo> register(@RequestBody Register user)  {
        try {
            System.out.println(new Gson().toJson(user));
            return userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST )
    public RtnResult<UserInfo> login(HttpServletRequest request ) {
        String username =request.getParameter("username");
        String password =request.getParameter("password");
        try {
            return userService.login(username,password);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public RtnResult<UserInfo> updateUserInfo(@RequestBody User user) {
        try {
            return userService.updateUserInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/mySubscribe",method = RequestMethod.POST)
    public RtnResult<PageInfo<UserInfo>> selectMyCareUsers(HttpServletRequest request) {
        try {
            return userService.selectMyCareUsers(request);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/recommend")
    public RtnResult<List<UserInfo>> selectUsersRecommend(HttpServletRequest request) {
        try {
            return userService.selectUsersRecommend(request.getParameter("username"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

}
