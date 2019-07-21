package com.xq.tmall.entity;

/**
 * 订单项实体类
 * @author 贤趣项目小组
 */
public class ProductOrderItem {
    private Integer productOrderItem_id/*订单项ID*/;
    private Short productOrderItem_number/*订单项产品数量*/;
    private Double productOrderItem_price/*订单项产品总价格*/;
    private String productOrderItem_userMessage/*订单项备注*/;
    private Product productOrderItem_product/*订单项对应产品*/;
    private ProductOrder productOrderItem_order/*订单项对应订单*/;
    private User productOrderItem_user/*订单项对应用户*/;
    //订单产品是否已经评价
    private Boolean isReview;

    public ProductOrderItem() {
    }

    public ProductOrderItem(Integer productOrderItem_id, Short productOrderItem_number, Double productOrderItem_price, Product productOrderItem_product, User productOrderItem_user, String productOrderItem_userMessage) {
        this.productOrderItem_id = productOrderItem_id;
        this.productOrderItem_number = productOrderItem_number;
        this.productOrderItem_price = productOrderItem_price;
        this.productOrderItem_product = productOrderItem_product;
        this.productOrderItem_user = productOrderItem_user;
        this.productOrderItem_userMessage = productOrderItem_userMessage;
    }

    @Override
    public String toString() {
        return "ProductOrderItem{" +
                "productOrderItem_id=" + productOrderItem_id +
                ", productOrderItem_number=" + productOrderItem_number +
                ", productOrderItem_price=" + productOrderItem_price +
                ", productOrderItem_product=" + productOrderItem_product +
                ", productOrderItem_order=" + productOrderItem_order +
                ", productOrderItem_user=" + productOrderItem_user +
                ", productOrderItem_userMessage='" + productOrderItem_userMessage + '\'' +
                '}';
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

    public ProductOrderItem setProductOrderItem_id(Integer productOrderItem_id) {
        this.productOrderItem_id = productOrderItem_id;
        return this;
    }

    public Short getProductOrderItem_number() {
        return productOrderItem_number;
    }

    public ProductOrderItem setProductOrderItem_number(Short productOrderItem_number) {
        this.productOrderItem_number = productOrderItem_number;
        return this;
    }

    public Double getProductOrderItem_price() {
        return productOrderItem_price;
    }

    public ProductOrderItem setProductOrderItem_price(Double productOrderItem_price) {
        this.productOrderItem_price = productOrderItem_price;
        return this;
    }

    public Product getProductOrderItem_product() {
        return productOrderItem_product;
    }

    public ProductOrderItem setProductOrderItem_product(Product productOrderItem_product) {
        this.productOrderItem_product = productOrderItem_product;
        return this;
    }

    public ProductOrder getProductOrderItem_order() {
        return productOrderItem_order;
    }

    public ProductOrderItem setProductOrderItem_order(ProductOrder productOrderItem_order) {
        this.productOrderItem_order = productOrderItem_order;
        return this;
    }

    public User getProductOrderItem_user() {
        return productOrderItem_user;
    }

    public ProductOrderItem setProductOrderItem_user(User productOrderItem_user) {
        this.productOrderItem_user = productOrderItem_user;
        return this;
    }

    public String getProductOrderItem_userMessage() {
        return productOrderItem_userMessage;
    }

    public ProductOrderItem setProductOrderItem_userMessage(String productOrderItem_userMessage) {
        this.productOrderItem_userMessage = productOrderItem_userMessage;
        return this;
    }

    public Boolean getIsReview() {
        return isReview;
    }

    public ProductOrderItem setIsReview(Boolean review) {
        isReview = review;
        return this;
    }
}
