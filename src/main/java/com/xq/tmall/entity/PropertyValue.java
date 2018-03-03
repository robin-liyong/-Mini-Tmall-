package com.xq.tmall.entity;

public class PropertyValue {
    private Integer propertyValue_id;
    private String propertyValue_value;
    private Property propertyValue_property;
    private Product propertyValue_product;

    public PropertyValue() {
    }

    public PropertyValue(Integer propertyValue_id, String propertyValue_value, Property propertyValue_property, Product propertyValue_product) {
        this.propertyValue_id = propertyValue_id;
        this.propertyValue_value = propertyValue_value;
        this.propertyValue_property = propertyValue_property;
        this.propertyValue_product = propertyValue_product;
    }

    public Integer getPropertyValue_id() {
        return propertyValue_id;
    }

    public void setPropertyValue_id(Integer propertyValue_id) {
        this.propertyValue_id = propertyValue_id;
    }

    public String getPropertyValue_value() {
        return propertyValue_value;
    }

    public void setPropertyValue_value(String propertyValue_value) {
        this.propertyValue_value = propertyValue_value;
    }

    public Property getPropertyValue_property() {
        return propertyValue_property;
    }

    public void setPropertyValue_property(Property propertyValue_property) {
        this.propertyValue_property = propertyValue_property;
    }

    public Product getPropertyValue_product() {
        return propertyValue_product;
    }

    public void setPropertyValue_product(Product propertyValue_product) {
        this.propertyValue_product = propertyValue_product;
    }
}
