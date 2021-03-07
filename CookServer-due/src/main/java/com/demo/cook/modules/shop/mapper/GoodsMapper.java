package com.demo.cook.modules.shop.mapper;

import com.demo.cook.modules.shop.model.Goods;
import com.demo.cook.modules.shop.model.QueryGoodsParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {

    List<Goods> selectGoodsList(QueryGoodsParams params)  throws Exception ;

    Goods queryGoodsDetails(@Param("goodsId") String goodsId) throws Exception ;

}