package com.demo.cook.modules.product.model;

import java.util.Date;

public class Product {
    private String productId;

    private String issuer;

    private String title;

    private String content;

    private String images;

    private String tagId;

    private Date createTime;

    public Product(String productId, String issuer, String title, String content, String images, String tagId, Date createTime) {
        this.productId = productId;
        this.issuer = issuer;
        this.title = title;
        this.content = content;
        this.images = images;
        this.tagId = tagId;
        this.createTime = createTime;
    }

    public Product() {
        super();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}