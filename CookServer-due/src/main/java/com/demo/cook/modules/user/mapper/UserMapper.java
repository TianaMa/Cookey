package com.demo.cook.modules.user.mapper;

import com.demo.cook.modules.user.model.Register;
import com.demo.cook.modules.user.model.User;
import com.demo.cook.modules.user.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {


    // 查询有没有被注册过
    int getCountUserByUsername(@Param("username") String username) throws Exception ;

    //注册
    int insertSelective(Register record) throws Exception ;

    //登录
    UserInfo selectUserByLogin(@Param("username") String username,@Param("password") String password) throws Exception ;

    //修改信息
    int updateUserInfoSelective(User record) throws Exception ;

    //查询用户信息
    UserInfo selectUserByUsername(@Param("username") String username) throws Exception ;

    //查询我关注的用户
    List<UserInfo> selectMyCareUsers(@Param("username") String username) throws Exception ;

    //查询（我没关注）推荐的用户 （根据粉丝数 倒叙查询）
    List<UserInfo>  selectUsersRecommend(@Param("username") String username) throws Exception ;

}