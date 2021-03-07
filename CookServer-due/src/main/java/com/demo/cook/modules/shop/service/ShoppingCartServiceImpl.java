package com.demo.cook.modules.shop.service;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.shop.mapper.ShoppingCartMapper;
import com.demo.cook.modules.shop.model.ShoppingCart;
import com.demo.cook.modules.shop.model.ShoppingCartDetails;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService{

    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public void setShoppingCartMapper(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @Override
    public RtnResult<Integer> addToShoppingCart(HttpServletRequest request) throws Exception {

        String username= request.getParameter("username");
        String goodsId=request.getParameter("goodsId");
        if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(goodsId)){
            return new RtnResult(Rtn.missingParameter);
        }
        ShoppingCart shoppingCart = shoppingCartMapper.queryShoppingCartByPrimaryKey(username, goodsId);
        if(shoppingCart==null){
            shoppingCartMapper.insertShoppingCart(username, goodsId);
        }else {
            shoppingCart.setBuyCount(shoppingCart.getBuyCount()+1);
            shoppingCartMapper.updateShoppingCartCount(shoppingCart);
        }

        Integer count = shoppingCartMapper.selectShoppingCartCount(username);
        count = count ==null?0:count;
        return new RtnResult(Rtn.success,count);
    }

    @Override
    public RtnResult<Integer> updateShoppingCartCount(ShoppingCart shoppingCart) throws Exception {

        shoppingCartMapper.updateShoppingCartCount(shoppingCart);
        Integer count = shoppingCartMapper.selectShoppingCartCount(shoppingCart.getUsername());
        count = count ==null?0:count;
        return new RtnResult(Rtn.success,count);
    }

    @Override
    public RtnResult<List<ShoppingCartDetails>> deleteShoppingCart(HttpServletRequest request) throws Exception {
        String username= request.getParameter("username");
        String goodsIds = request.getParameter("goodsIds");
        if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(goodsIds)){
            return new RtnResult(Rtn.missingParameter);
        }
        shoppingCartMapper.deleteShoppingCart(username,goodsIds.split(","));
        return new RtnResult(Rtn.success,shoppingCartMapper.queryShoppingCart(username));
    }

    @Override
    public RtnResult<Integer> queryShoppingCartCount(String username) throws Exception {
        if (StringUtils.isNullOrEmpty(username)){
            return new RtnResult<>(Rtn.success,0);
        }
        Integer count = shoppingCartMapper.selectShoppingCartCount(username);
        count = count ==null?0:count;
        return new RtnResult<>(Rtn.success,count);
    }

    @Override
    public RtnResult<List<ShoppingCartDetails>> queryShoppingCart(String username) throws Exception {
        if(StringUtils.isNullOrEmpty(username)){
            return new RtnResult(Rtn.missingParameter);
        }
        return new RtnResult(Rtn.success,shoppingCartMapper.queryShoppingCart(username));
    }
}
