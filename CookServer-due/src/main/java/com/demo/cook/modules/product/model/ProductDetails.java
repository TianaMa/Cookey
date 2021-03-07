package com.demo.cook.modules.product.model;


import com.demo.cook.modules.comment.model.CommentDetails;

import java.util.List;

public class ProductDetails extends Product{


    private String tagName;

    private String nickname;

    private String headImg;

    private int countPraise;

    private int countCollect;

    private int countComment;

    private boolean collected;

    private boolean praised;

    private List<CommentDetails> commentList;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    public int getCountPraise() {
        return countPraise;
    }

    public void setCountPraise(int countPraise) {
        this.countPraise = countPraise;
    }

    public int getCountCollect() {
        return countCollect;
    }

    public void setCountCollect(int countCollect) {
        this.countCollect = countCollect;
    }


    public int getCountComment() {
        return countComment;
    }

    public void setCountComment(int countComment) {
        this.countComment = countComment;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public boolean isPraised() {
        return praised;
    }

    public void setPraised(boolean praised) {
        this.praised = praised;
    }

    public List<CommentDetails> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDetails> commentList) {
        this.commentList = commentList;
    }
}
