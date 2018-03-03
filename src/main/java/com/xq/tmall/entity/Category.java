package com.xq.tmall.entity;

public class Category {
    private Integer category_id;
    private String category_name;
    private String category_image_src;

    public Category(){

    }

    public Category(Integer category_id, String category_name, String category_image_src) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_image_src = category_image_src;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image_src() {
        return category_image_src;
    }

    public void setCategory_image_src(String category_image_src) {
        this.category_image_src = category_image_src;
    }
}
