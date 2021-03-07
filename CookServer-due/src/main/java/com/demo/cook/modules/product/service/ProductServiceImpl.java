package com.demo.cook.modules.product.service;


import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.comment.service.ICommentService;
import com.demo.cook.modules.product.mapper.ProductMapper;
import com.demo.cook.modules.product.model.Product;
import com.demo.cook.modules.product.model.ProductDetails;
import com.demo.cook.modules.product.model.QueryProductParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    private ProductMapper productMapper;

    private ICommentService commentService;

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public RtnResult publish(Product product) throws Exception {

        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        productMapper.insertProduct(product);
        return new RtnResult<>(Rtn.success);
    }


    @Override
    public RtnResult updateMyProduct(Product product) throws Exception {
        return new RtnResult(Rtn.success,productMapper.updateByProductIdSelective(product));
    }

    @Override
    public RtnResult<PageInfo<ProductDetails>> queryProductList(QueryProductParams request) throws Exception {

        PageHelper.startPage(request.getPageNum(), request.getPageSize(),true);
        List<ProductDetails> productDetails = productMapper.queryProductList(request);


        Map<String,String> params = new HashMap<>();
        params.put("loginUserName",request.getLoginUserName());

        for (ProductDetails product:productDetails){
            params.put("targetId",product.getProductId());
            product.setCommentList(commentService.queryCommentList(params).getData());
        }

        PageInfo<ProductDetails> appsPageInfo = new PageInfo(productDetails);

        return new RtnResult<>(Rtn.success,appsPageInfo);
    }


}
