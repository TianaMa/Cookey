package com.demo.cook.modules.product.controller;


import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.product.model.Product;
import com.demo.cook.modules.product.model.ProductDetails;
import com.demo.cook.modules.product.model.QueryProductParams;
import com.demo.cook.modules.product.service.IProductService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    private IProductService productService;

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public RtnResult publish(@RequestBody Product product)  {
        try {
            System.out.println(new Gson().toJson(product));
            return productService.publish(product);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }


    @RequestMapping(value = "/updateMyProduct",method = RequestMethod.POST)
    public RtnResult updateMyProduct(@RequestBody Product product)  {
        try {
            return productService.updateMyProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }



    /**
     * 根据标签（tagId）查询、
     * 关键字(searchText)查询
     * 发布者(issuer )查询  、
     * 排序方式(order = ["collect","praise","默认是按时间倒序"])查询、
     * 支持分页
     * */
    @RequestMapping(value = "/queryProductList",method = RequestMethod.POST)
    public RtnResult<PageInfo<ProductDetails>> queryProductList(@RequestBody QueryProductParams params)  {
        try {
            return productService.queryProductList(params);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }



}
