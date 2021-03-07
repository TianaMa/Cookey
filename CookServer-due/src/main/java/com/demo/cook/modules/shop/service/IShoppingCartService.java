package com.demo.cook.modules.shop.service;

import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.shop.model.ShoppingCart;
import com.demo.cook.modules.shop.model.ShoppingCartDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IShoppingCartService {

    RtnResult<Integer> addToShoppingCart(HttpServletRequest request) throws Exception;

    RtnResult<Integer> updateShoppingCartCount(ShoppingCart shoppingCart) throws Exception;

    RtnResult<List<ShoppingCartDetails>> deleteShoppingCart(HttpServletRequest request) throws Exception;

    RtnResult<Integer> queryShoppingCartCount(String username) throws Exception;

    RtnResult<List<ShoppingCartDetails>> queryShoppingCart(String username) throws Exception;
}
