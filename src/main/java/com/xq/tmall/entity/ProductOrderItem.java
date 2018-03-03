package com.xq.tmall.entity;

public class ProductOrderItem {
    private Integer productOrderItem_id;
    private Short productOrderItem_number;
    private Double productOrderItem_price;
    private Product productOrderItem_product;
    private ProductOrder productOrderItem_order;
    private User productOrderItem_user;

    public ProductOrderItem() {
    }

    public ProductOrderItem(Integer productOrderItem_id, Short productOrderItem_number, Double productOrderItem_price, Product productOrderItem_product, User productOrderItem_user) {
        this.productOrderItem_id = productOrderItem_id;
        this.productOrderItem_number = productOrderItem_number;
        this.productOrderItem_price = productOrderItem_price;
        this.productOrderItem_product = productOrderItem_product;
        this.productOrderItem_user = productOrderItem_user;
    }

    public ProductOrderItem(Integer productOrderItem_id, Short productOrderItem_number, Double productOrderItem_price, Product productOrderItem_product, ProductOrder productOrderItem_order, User productOrderItem_user) {
        this.productOrderItem_id = productOrderItem_id;
        this.productOrderItem_number = productOrderItem_number;
        this.productOrderItem_price = productOrderItem_price;
        this.productOrderItem_product = productOrderItem_product;
        this.productOrderItem_order = productOrderItem_order;
        this.productOrderItem_user = productOrderItem_user;
    }

    public Integer getProductOrderItem_id() {
        return productOrderItem_id;
    }

    public void setProductOrderItem_id(Integer productOrderItem_id) {
        this.productOrderItem_id = productOrderItem_id;
    }

    public Short getProductOrderItem_number() {
        return productOrderItem_number;
    }

    public void setProductOrderItem_number(Short productOrderItem_number) {
        this.productOrderItem_number = productOrderItem_number;
    }

    public Double getProductOrderItem_price() {
        return productOrderItem_price;
    }

    public void setProductOrderItem_price(Double productOrderItem_price) {
        this.productOrderItem_price = productOrderItem_price;
    }

    public Product getProductOrderItem_product() {
        return productOrderItem_product;
    }

    public void setProductOrderItem_product(Product productOrderItem_product) {
        this.productOrderItem_product = productOrderItem_product;
    }

    public ProductOrder getProductOrderItem_order() {
        return productOrderItem_order;
    }

    public void setProductOrderItem_order(ProductOrder productOrderItem_order) {
        this.productOrderItem_order = productOrderItem_order;
    }

    public User getProductOrderItem_user() {
        return productOrderItem_user;
    }

    public void setProductOrderItem_user(User productOrderItem_user) {
        this.productOrderItem_user = productOrderItem_user;
    }
}
