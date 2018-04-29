package com.xq.tmall.service;

import com.xq.tmall.entity.Product;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;

import java.util.List;

public interface ProductService {
    boolean add(Product product);
    boolean update(Product product);

    List<Product> getList(Product product, Byte[] product_isEnabled_array, OrderUtil orderUtil, PageUtil pageUtil);

    List<Product> getTitle(Product product, PageUtil pageUtil);
    Product get(Integer product_Id);
    Integer getTotal(Product product,Byte[] product_isEnabled_array);
}
