package com.demo.cook.modules.shop.controller;


import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.shop.model.ShoppingCart;
import com.demo.cook.modules.shop.model.ShoppingCartDetails;
import com.demo.cook.modules.shop.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private IShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(IShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    RtnResult<Integer> addToShoppingCart(HttpServletRequest request){
        try {
            return shoppingCartService.addToShoppingCart(request);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/updateCount",method = RequestMethod.POST)
    RtnResult<Integer> updateShoppingCartCount(@RequestBody ShoppingCart request){
        try {
            return shoppingCartService.updateShoppingCartCount(request);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }



    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    RtnResult<List<ShoppingCartDetails>> deleteShoppingCart(HttpServletRequest request){
        try {
            return shoppingCartService.deleteShoppingCart(request);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/queryCount",method = RequestMethod.GET)
    RtnResult<Integer> queryShoppingCartCount(HttpServletRequest request){
        try {
            return shoppingCartService.queryShoppingCartCount(request.getParameter("username"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    RtnResult<List<ShoppingCartDetails>> queryShoppingCart(HttpServletRequest request){
        try {
            return shoppingCartService.queryShoppingCart(request.getParameter("username"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

}
