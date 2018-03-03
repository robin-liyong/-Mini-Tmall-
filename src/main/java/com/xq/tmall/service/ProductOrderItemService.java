package com.xq.tmall.service;

import com.xq.tmall.entity.ProductOrderItem;
import com.xq.tmall.util.PageUtil;

import java.util.List;

public interface ProductOrderItemService {
    boolean add(ProductOrderItem productOrderItem);
    boolean update(ProductOrderItem productOrderItem);
    boolean deleteList(Integer[] productOrderItem_id_list);

    List<ProductOrderItem> getList(PageUtil pageUtil);
    List<ProductOrderItem> getListByOrderId(Integer order_id, PageUtil pageUtil);
    List<ProductOrderItem> getListByUserId(Integer user_id, PageUtil pageUtil);
    List<ProductOrderItem> getListByProductId(Integer product_id, PageUtil pageUtil);
    ProductOrderItem get(Integer productOrderItem_id);
    Integer getTotal();
    Integer getTotalByOrderId(Integer order_id);
    Integer getTotalByUserId(Integer user_id);
    Integer getTotalByProductId(Integer product_id);
    Integer getSaleCountByProductId(Integer product_id);
}
