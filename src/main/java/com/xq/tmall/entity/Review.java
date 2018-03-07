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

    public Review setReview_id(Integer review_id) {
        this.review_id = review_id;
        return this;
    }

    public String getReview_content() {
        return review_content;
    }

    public Review setReview_content(String review_content) {
        this.review_content = review_content;
        return this;
    }

    public Date getReview_createDate() {
        return review_createDate;
    }

    public Review setReview_createDate(Date review_createDate) {
        this.review_createDate = review_createDate;
        return this;
    }

    public User getReview_user() {
        return review_user;
    }

    public Review setReview_user(User review_user) {
        this.review_user = review_user;
        return this;
    }

    public Product getReview_product() {
        return review_product;
    }

    public Review setReview_product(Product review_product) {
        this.review_product = review_product;
        return this;
    }
}
