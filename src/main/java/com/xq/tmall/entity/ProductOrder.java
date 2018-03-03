package com.xq.tmall.entity;

import java.util.Date;
import java.util.List;

public class ProductOrder {
    private Integer productOrder_id;
    private String productOrder_code;
    private Address productOrder_address;
    private String productOrder_detail_address;
    private String productOrder_post;
    private String productOrder_receiver;
    private String productOrder_mobile;
    private String productOrder_userMessage;
    private Date productOrder_pay_date;
    private Date productOrder_delivery_date;
    private Date productOrder_confirm_date;
    private Byte productOrder_status;
    private User productOrder_user;
    private List<ProductOrderItem> productOrderItemList;

    public ProductOrder() {
    }

    public ProductOrder(Integer productOrder_id, String productOrder_code, Address productOrder_address, String productOrder_detail_address, String productOrder_post, String productOrder_receiver, String productOrder_mobile, Date productOrder_pay_date, Byte productOrder_status, User productOrder_user) {
        this.productOrder_id = productOrder_id;
        this.productOrder_code = productOrder_code;
        this.productOrder_address = productOrder_address;
        this.productOrder_detail_address = productOrder_detail_address;
        this.productOrder_post = productOrder_post;
        this.productOrder_receiver = productOrder_receiver;
        this.productOrder_mobile = productOrder_mobile;
        this.productOrder_pay_date = productOrder_pay_date;
        this.productOrder_status = productOrder_status;
        this.productOrder_user = productOrder_user;
    }

    public ProductOrder(Integer productOrder_id, String productOrder_code, Address productOrder_address, String productOrder_detail_address, String productOrder_post, String productOrder_receiver, String productOrder_mobile, String productOrder_userMessage, Date productOrder_pay_date, Date productOrder_delivery_date, Date productOrder_confirm_date, Byte productOrder_status, User productOrder_user, List<ProductOrderItem> productOrderItemList) {
        this.productOrder_id = productOrder_id;
        this.productOrder_code = productOrder_code;
        this.productOrder_address = productOrder_address;
        this.productOrder_detail_address = productOrder_detail_address;
        this.productOrder_post = productOrder_post;
        this.productOrder_receiver = productOrder_receiver;
        this.productOrder_mobile = productOrder_mobile;
        this.productOrder_userMessage = productOrder_userMessage;
        this.productOrder_pay_date = productOrder_pay_date;
        this.productOrder_delivery_date = productOrder_delivery_date;
        this.productOrder_confirm_date = productOrder_confirm_date;
        this.productOrder_status = productOrder_status;
        this.productOrder_user = productOrder_user;
        this.productOrderItemList = productOrderItemList;
    }

    public Integer getProductOrder_id() {
        return productOrder_id;
    }

    public void setProductOrder_id(Integer productOrder_id) {
        this.productOrder_id = productOrder_id;
    }

    public String getProductOrder_code() {
        return productOrder_code;
    }

    public void setProductOrder_code(String productOrder_code) {
        this.productOrder_code = productOrder_code;
    }

    public Address getProductOrder_address() {
        return productOrder_address;
    }

    public void setProductOrder_address(Address productOrder_address) {
        this.productOrder_address = productOrder_address;
    }

    public String getProductOrder_detail_address() {
        return productOrder_detail_address;
    }

    public void setProductOrder_detail_address(String productOrder_detail_address) {
        this.productOrder_detail_address = productOrder_detail_address;
    }

    public String getProductOrder_post() {
        return productOrder_post;
    }

    public void setProductOrder_post(String productOrder_post) {
        this.productOrder_post = productOrder_post;
    }

    public String getProductOrder_receiver() {
        return productOrder_receiver;
    }

    public void setProductOrder_receiver(String productOrder_receiver) {
        this.productOrder_receiver = productOrder_receiver;
    }

    public String getProductOrder_mobile() {
        return productOrder_mobile;
    }

    public void setProductOrder_mobile(String productOrder_mobile) {
        this.productOrder_mobile = productOrder_mobile;
    }

    public String getProductOrder_userMessage() {
        return productOrder_userMessage;
    }

    public void setProductOrder_userMessage(String productOrder_userMessage) {
        this.productOrder_userMessage = productOrder_userMessage;
    }

    public Date getProductOrder_pay_date() {
        return productOrder_pay_date;
    }

    public void setProductOrder_pay_date(Date productOrder_pay_date) {
        this.productOrder_pay_date = productOrder_pay_date;
    }

    public Date getProductOrder_delivery_date() {
        return productOrder_delivery_date;
    }

    public void setProductOrder_delivery_date(Date productOrder_delivery_date) {
        this.productOrder_delivery_date = productOrder_delivery_date;
    }

    public Date getProductOrder_confirm_date() {
        return productOrder_confirm_date;
    }

    public void setProductOrder_confirm_date(Date productOrder_confirm_date) {
        this.productOrder_confirm_date = productOrder_confirm_date;
    }

    public Byte getProductOrder_status() {
        return productOrder_status;
    }

    public void setProductOrder_status(Byte productOrder_status) {
        this.productOrder_status = productOrder_status;
    }

    public User getProductOrder_user() {
        return productOrder_user;
    }

    public void setProductOrder_user(User productOrder_user) {
        this.productOrder_user = productOrder_user;
    }

    public List<ProductOrderItem> getProductOrderItemList() {
        return productOrderItemList;
    }

    public void setProductOrderItemList(List<ProductOrderItem> productOrderItemList) {
        this.productOrderItemList = productOrderItemList;
    }
}
