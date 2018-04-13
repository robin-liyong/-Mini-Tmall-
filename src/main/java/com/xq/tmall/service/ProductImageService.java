package com.xq.tmall.service;

import com.xq.tmall.entity.ProductImage;
import com.xq.tmall.util.PageUtil;

import java.util.List;

public interface ProductImageService {
    boolean add(ProductImage productImage);

    boolean addList(List<ProductImage> productImageList);
    boolean update(ProductImage productImage);
    boolean deleteList(Integer[] productImage_id_list);

    List<ProductImage> getList(Integer product_id, Byte productImage_type, PageUtil pageUtil);
    ProductImage get(Integer productImage_id);
    Integer getTotal(Integer product_id, Byte productImage_type);
}
