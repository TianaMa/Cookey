package com.demo.cook.modules.product.mapper;

import com.demo.cook.modules.product.model.Product;
import com.demo.cook.modules.product.model.ProductDetails;
import com.demo.cook.modules.product.model.QueryProductParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ProductMapper {

    int insertProduct(Product product) throws Exception;


    int deleteByPrimaryKey(String productId) throws Exception;

    int updateByProductIdSelective(Product record) throws Exception;

    List<ProductDetails> queryProductList(QueryProductParams params) throws Exception;

}