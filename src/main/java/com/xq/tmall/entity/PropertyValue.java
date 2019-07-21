package com.xq.tmall.entity;

/**
 * 属性值实体类
 * @author 贤趣项目小组
 */
public class PropertyValue {
    private Integer propertyValue_id/*属性值ID*/;
    private String propertyValue_value/*属性值Value*/;
    private Property propertyValue_property/*属性值对应属性*/;
    private Product propertyValue_product/*属性值对应产品*/;

    public PropertyValue() {
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "propertyValue_id=" + propertyValue_id +
                ", propertyValue_value='" + propertyValue_value + '\'' +
                ", propertyValue_property=" + propertyValue_property +
                ", propertyValue_product=" + propertyValue_product +
                '}';
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

    public PropertyValue setPropertyValue_id(Integer propertyValue_id) {
        this.propertyValue_id = propertyValue_id;
        return this;
    }

    public String getPropertyValue_value() {
        return propertyValue_value;
    }

    public PropertyValue setPropertyValue_value(String propertyValue_value) {
        this.propertyValue_value = propertyValue_value;
        return this;
    }

    public Property getPropertyValue_property() {
        return propertyValue_property;
    }

    public PropertyValue setPropertyValue_property(Property propertyValue_property) {
        this.propertyValue_property = propertyValue_property;
        return this;
    }

    public Product getPropertyValue_product() {
        return propertyValue_product;
    }

    public PropertyValue setPropertyValue_product(Product propertyValue_product) {
        this.propertyValue_product = propertyValue_product;
        return this;
    }
}
