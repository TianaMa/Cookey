package com.demo.cook.modules.shop.service;

import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.shop.model.Goods;
import com.demo.cook.modules.shop.model.QueryGoodsParams;
import com.github.pagehelper.PageInfo;

public interface IGoodsService {

    RtnResult<PageInfo<Goods>> queryGoodsList(QueryGoodsParams request) throws Exception;

    RtnResult<Goods> queryGoodsDetails(String goodsId) throws Exception;

}
