package com.xq.tmall.entity;

public class ProductImage {
    private Integer productImage_id;
    private Byte productImage_type;
    private String productImage_src;
    private Product productImage_product;

    public ProductImage(){

    }

    public ProductImage(Integer productImage_id, Byte productImage_type, String productImage_src, Product productImage_product) {
        this.productImage_id = productImage_id;
        this.productImage_type = productImage_type;
        this.productImage_src = productImage_src;
        this.productImage_product = productImage_product;
    }

    public Integer getProductImage_id() {
        return productImage_id;
    }

    public void setProductImage_id(Integer productImage_id) {
        this.productImage_id = productImage_id;
    }

    public Byte getProductImage_type() {
        return productImage_type;
    }

    public void setProductImage_type(Byte productImage_type) {
        this.productImage_type = productImage_type;
    }

    public String getProductImage_src() {
        return productImage_src;
    }

    public void setProductImage_src(String productImage_src) {
        this.productImage_src = productImage_src;
    }

    public Product getProductImage_product() {
        return productImage_product;
    }

    public void setProductImage_product(Product productImage_product) {
        this.productImage_product = productImage_product;
    }
}
