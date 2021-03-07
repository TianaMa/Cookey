package com.demo.cook.modules.product.model;

import java.util.Date;

public class ProductTag {

    private String tagId;

    private String tagName;



    private int  productCount;

    public ProductTag(String tagId, String tagName, Date createTime) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public ProductTag() {
        super();
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }


}