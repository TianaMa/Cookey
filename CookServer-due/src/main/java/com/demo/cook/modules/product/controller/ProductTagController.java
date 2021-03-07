package com.demo.cook.modules.product.controller;

import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.product.model.ProductTag;
import com.demo.cook.modules.product.service.IProductTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/productTag")
public class ProductTagController {

    private IProductTagService productTagService;

    @Autowired
    public void setProductTagService(IProductTagService productTagService) {
        this.productTagService = productTagService;
    }

    @RequestMapping(value = "/queryList",method = RequestMethod.GET)
    public RtnResult<List<ProductTag>> queryProductTags(HttpServletRequest request){

        System.out.println("queryTagList");

        return productTagService.queryProductTags();
    }

}
