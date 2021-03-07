package com.demo.cook.modules.shop.mapper;

import com.demo.cook.modules.shop.model.ShoppingCart;
import com.demo.cook.modules.shop.model.ShoppingCartDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    ShoppingCart queryShoppingCartByPrimaryKey(@Param("username") String username, @Param("goodsId") String goodsId)  throws Exception;

    int insertShoppingCart(@Param("username") String username, @Param("goodsId") String goodsId) throws Exception;

    int updateShoppingCartCount(ShoppingCart shoppingCart) throws Exception;

    Integer selectShoppingCartCount(@Param("username") String username) throws Exception;

    int deleteShoppingCart(@Param("username") String username,  String [] goodsIds) throws Exception;


    List<ShoppingCartDetails> queryShoppingCart(@Param("username") String username) throws Exception;

}