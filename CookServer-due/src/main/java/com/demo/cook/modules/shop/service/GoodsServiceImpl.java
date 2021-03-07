package com.demo.cook.modules.shop.service;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.shop.mapper.GoodsMapper;
import com.demo.cook.modules.shop.model.Goods;
import com.demo.cook.modules.shop.model.QueryGoodsParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsServiceImpl implements IGoodsService{

    private GoodsMapper goodsMapper;

    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public RtnResult<PageInfo<Goods>> queryGoodsList(QueryGoodsParams request) throws Exception {

        PageHelper.startPage(request.getPageNum(), request.getPageSize(),true);
        List<Goods> goods = goodsMapper.selectGoodsList(request);
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        return new RtnResult<>(Rtn.success,pageInfo);
    }

    @Override
    public RtnResult<Goods> queryGoodsDetails(String goodsId) throws Exception {
        if (StringUtils.isNullOrEmpty(goodsId)){
            return new RtnResult<>(Rtn.missingParameter);
        }
        Goods goods= goodsMapper.queryGoodsDetails(goodsId);
        if(goods==null){
            return new RtnResult<>(Rtn.noData);
        }
        return new RtnResult<>(Rtn.success,goods);
    }
}
