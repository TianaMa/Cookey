package com.demo.cook.modules.user.model;

public class UserInfo extends User{

    //作品数
    private int countProduct;
    //菜谱数
    private int countRecipe;
    //被关注数（粉丝）
    private int countBeCared;
    //关注数
    private int countSubscribe;
    //收藏数
    private int countCollect;
    //是否已关注
    private boolean subscribe;

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    public int getCountRecipe() {
        return countRecipe;
    }

    public void setCountRecipe(int countRecipe) {
        this.countRecipe = countRecipe;
    }

    public int getCountBeCared() {
        return countBeCared;
    }

    public void setCountBeCared(int countBeCared) {
        this.countBeCared = countBeCared;
    }

    public int getCountSubscribe() {
        return countSubscribe;
    }

    public void setCountSubscribe(int countSubscribe) {
        this.countSubscribe = countSubscribe;
    }

    public int getCountCollect() {
        return countCollect;
    }

    public void setCountCollect(int countCollect) {
        this.countCollect = countCollect;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }
}
