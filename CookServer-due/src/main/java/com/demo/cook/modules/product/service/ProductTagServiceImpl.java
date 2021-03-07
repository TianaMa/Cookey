package com.demo.cook.modules.product.service;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.product.mapper.ProductTagMapper;
import com.demo.cook.modules.product.model.ProductTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductTagServiceImpl implements IProductTagService {

    private ProductTagMapper productTagMapper;

    @Autowired
    public void setProductTagMapper(ProductTagMapper productTagMapper) {
        this.productTagMapper = productTagMapper;
    }

    @Override
    public RtnResult<List<ProductTag>> queryProductTags() {
        return new RtnResult<>(Rtn.success,productTagMapper.selectProductTags());
    }
}
