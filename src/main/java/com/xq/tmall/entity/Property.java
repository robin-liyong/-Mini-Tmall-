package com.xq.tmall.entity;

import java.util.List;

public class Property {
    private Integer property_id;
    private String property_name;
    private Category property_category;
    private List<PropertyValue> propertyValueList;

    public Property() {
    }

    public Property(Integer property_id, String property_name, Category property_category, List<PropertyValue> propertyValueList) {
        this.property_id = property_id;
        this.property_name = property_name;
        this.property_category = property_category;
        this.propertyValueList = propertyValueList;
    }

    public Integer getProperty_id() {
        return property_id;
    }

    public void setProperty_id(Integer property_id) {
        this.property_id = property_id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public Category getProperty_category() {
        return property_category;
    }

    public void setProperty_category(Category property_category) {
        this.property_category = property_category;
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }
}
