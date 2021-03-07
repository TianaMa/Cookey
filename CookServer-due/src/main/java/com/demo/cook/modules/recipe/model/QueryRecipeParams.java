package com.demo.cook.modules.recipe.model;


public class QueryRecipeParams {

    public enum Order{

        collect("collect"),praise("praise"),time("time");

        private String type;
        Order(String type){
            this.type=type;
        }

        public String getType(){
            return type;
        }

    }

    private int pageNum;
    private int pageSize ;
    private String loginUserName;

    private String sortId;
    private String searchText;
    private String issuer;

    private String collector;
    private String order;

    public QueryRecipeParams() {
    }

    public QueryRecipeParams(String issuer) {
        this.issuer = issuer;
    }

    public QueryRecipeParams(Order order) {
        this.order = order.getType();
    }


    public int getPageNum() {
        return pageNum>0?pageNum:1;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize>0?pageSize:20;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order.getType();
    }


}
