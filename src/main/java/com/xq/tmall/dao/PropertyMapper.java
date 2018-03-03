package com.xq.tmall.dao;

import com.xq.tmall.entity.Property;
import com.xq.tmall.util.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PropertyMapper {
    Integer insertOne(@Param("property") Property property);
    Integer updateOne(@Param("property") Property property);
    Integer deleteList(@Param("property_id_list") Integer[] property_id_list);

    List<Property> select(@Param("property") Property property, @Param("pageUtil") PageUtil pageUtil);
    Property selectOne(@Param("property_id") Integer property_id);
    Integer selectTotal(@Param("property") Property property);
}
