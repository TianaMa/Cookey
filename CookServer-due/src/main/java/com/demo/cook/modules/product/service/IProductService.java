package com.demo.cook.modules.product.service;

import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.product.model.Product;
import com.demo.cook.modules.product.model.ProductDetails;
import com.demo.cook.modules.product.model.QueryProductParams;
import com.github.pagehelper.PageInfo;


public interface IProductService {

    RtnResult publish(Product product) throws Exception;


    RtnResult updateMyProduct(Product product) throws Exception;


    RtnResult<PageInfo<ProductDetails>> queryProductList(QueryProductParams request) throws Exception;

}
