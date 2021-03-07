package com.demo.cook.modules.user.service;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.product.model.ProductDetails;
import com.demo.cook.modules.user.mapper.UserMapper;
import com.demo.cook.modules.user.model.Register;
import com.demo.cook.modules.user.model.User;
import com.demo.cook.modules.user.model.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {


    UserMapper userMapper;

    @Autowired
    public void setUserDao (UserMapper userDao) {
        this.userMapper = userDao;
    }

    @Override
    public RtnResult<UserInfo> register(Register user) throws Exception {
        int existUserCount = userMapper.getCountUserByUsername(user.getUsername());
        if(existUserCount>0){
            return new RtnResult(Rtn.userAlreadyExists);
        }
        int count=userMapper.insertSelective(user);
        if(count>0){
            UserInfo newUser = userMapper.selectUserByUsername(user.getUsername());
            return new RtnResult(Rtn.success,newUser);
        }else {
            return new RtnResult(Rtn.registerFail);
        }
    }

    @Override
    public RtnResult<UserInfo> login(String username, String password) throws Exception {
        if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)){
            return new RtnResult<>(Rtn.missingParameter);
        }

        UserInfo user = userMapper.selectUserByLogin(username,password);
        if(user==null){
            return new RtnResult<>(Rtn.userOrPasswordError);
        }else {
            return new RtnResult(Rtn.success,user);
        }
    }

    @Override
    public RtnResult<UserInfo> updateUserInfo(User user) throws Exception {
        int updateCount=userMapper.updateUserInfoSelective(user);
        if(updateCount>0){
            User newUser = userMapper.selectUserByUsername(user.getUsername());
            return new RtnResult(Rtn.success,newUser);
        }else {
            return new RtnResult(Rtn.editUserInfoError);
        }
    }


    @Override
    public RtnResult<PageInfo<UserInfo>> selectMyCareUsers(HttpServletRequest request) throws Exception {

        int pageNum= ServletRequestUtils.getIntParameter(request,"pageNum",1);
        int pageSize= ServletRequestUtils.getIntParameter(request,"pageSize",20);
        PageHelper.startPage(pageNum, pageSize,true);
        List<UserInfo> myCareUsers = userMapper.selectMyCareUsers(request.getParameter("username"));
        PageInfo<UserInfo> appsPageInfo = new PageInfo(myCareUsers);


        return new RtnResult<>(Rtn.success,appsPageInfo);
    }

    @Override
    public RtnResult<List<UserInfo>> selectUsersRecommend(String username) throws Exception {
        PageHelper.startPage(1, 20,true);
        return new RtnResult<>(Rtn.success,userMapper.selectUsersRecommend(username));
    }


}
