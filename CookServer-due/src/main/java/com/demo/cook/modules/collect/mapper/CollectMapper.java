package com.demo.cook.modules.collect.mapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CollectMapper {



    int addCollect(String username, String targetId) throws Exception;


    int cancelCollect(String username, String targetId) throws Exception;

}