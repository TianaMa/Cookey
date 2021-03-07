package com.demo.cook.modules.subscribe.mapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SubscribeMapper {



    int addSubscribe(String username, String targetUser) throws Exception;


    int cancelSubscribe(String username, String targetUser) throws Exception;

}