package com.xq.tmall.entity;

import java.util.Date;

public class Review {
    private Integer review_id;
    private String review_content;
    private Date review_createDate;
    private User review_user;
    private Product review_product;

    public Review() {
    }

    public Review(Integer review_id, String review_content, User review_user, Product review_product) {
        this.review_id = review_id;
        this.review_content = review_content;
        this.review_user = review_user;
        this.review_product = review_product;
    }

    public Review(Integer review_id, String review_content, Date review_createDate, User review_user, Product review_product) {
        this.review_id = review_id;
        this.review_content = review_content;
        this.review_createDate = review_createDate;
        this.review_user = review_user;
        this.review_product = review_product;
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public Date getReview_createDate() {
        return review_createDate;
    }

    public void setReview_createDate(Date review_createDate) {
        this.review_createDate = review_createDate;
    }

    public User getReview_user() {
        return review_user;
    }

    public void setReview_user(User review_user) {
        this.review_user = review_user;
    }

    public Product getReview_product() {
        return review_product;
    }

    public void setReview_product(Product review_product) {
        this.review_product = review_product;
    }
}
