package com.demo.cook.modules.praise.mapper;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface PraiseMapper {



    int addPraise(String username, String targetId) throws Exception;


    int cancelPraise(String username, String targetId) throws Exception;

}