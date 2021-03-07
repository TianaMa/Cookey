package com.demo.cook.modules.product.service;

import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.product.model.ProductTag;

import java.util.List;

public interface IProductTagService {

    RtnResult<List<ProductTag>> queryProductTags();
}
