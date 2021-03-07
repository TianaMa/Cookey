package com.demo.cook.modules.product.mapper;

import com.demo.cook.modules.product.model.ProductTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ProductTagMapper {

    List<ProductTag> selectProductTags();

}