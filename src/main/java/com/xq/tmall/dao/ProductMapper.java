package com.xq.tmall.dao;

import com.xq.tmall.entity.Product;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    Integer insertOne(@Param("product") Product product);
    Integer updateOne(@Param("product") Product product);

    List<Product> select(@Param("product") Product product,@Param("product_isEnabled_array") Byte[] product_isEnabled_array, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    List<Product> selectTitle(@Param("product") Product product, @Param("pageUtil") PageUtil pageUtil);
    Product selectOne(@Param("product_id") Integer product_Id);
    Integer selectTotal(@Param("product") Product product,@Param("product_isEnabled_array") Byte[] product_isEnabled_array);
}
