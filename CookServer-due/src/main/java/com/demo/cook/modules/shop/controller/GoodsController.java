package com.demo.cook.modules.shop.controller;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.shop.model.Goods;
import com.demo.cook.modules.shop.model.QueryGoodsParams;
import com.demo.cook.modules.shop.service.IGoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private IGoodsService goodsService;

    @Autowired
    public void setGoodsService(IGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value = "/queryGoodsList",method = RequestMethod.POST)
    RtnResult<PageInfo<Goods>> queryGoodsList(@RequestBody QueryGoodsParams params){
        try {
            return goodsService.queryGoodsList(params);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/queryGoodsDetails",method = RequestMethod.GET)
    RtnResult<Goods> queryGoodsDetails(HttpServletRequest params){
        try {
            return goodsService.queryGoodsDetails(params.getParameter("goodsId"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }
}
