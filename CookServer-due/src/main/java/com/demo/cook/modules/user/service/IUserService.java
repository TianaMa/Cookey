package com.demo.cook.modules.user.service;

import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.user.model.Register;
import com.demo.cook.modules.user.model.User;
import com.demo.cook.modules.user.model.UserInfo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IUserService {


    RtnResult<UserInfo> register(Register user) throws Exception;

    RtnResult<UserInfo> login(String username,String password) throws Exception;

    RtnResult<UserInfo> updateUserInfo(User user) throws Exception;

    RtnResult<PageInfo<UserInfo>> selectMyCareUsers(HttpServletRequest request) throws Exception;

    RtnResult<List<UserInfo>> selectUsersRecommend(String username) throws Exception;


}
