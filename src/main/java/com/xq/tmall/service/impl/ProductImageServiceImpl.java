package com.xq.tmall.service.impl;

import com.xq.tmall.dao.ProductImageMapper;
import com.xq.tmall.entity.ProductImage;
import com.xq.tmall.service.ProductImageService;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService{
    private ProductImageMapper productImageMapper;
    @Resource(name = "productImageMapper")
    public void setProductImageMapper(ProductImageMapper productImageMapper) {
        this.productImageMapper = productImageMapper;
    }

    @Override
    public boolean add(ProductImage productImage) {
        return productImageMapper.insertOne(productImage)>0;
    }

    @Override
    public boolean update(ProductImage productImage) {
        return productImageMapper.updateOne(productImage)>0;
    }

    @Override
    public boolean deleteList(Integer[] productImage_id_list) {
        return productImageMapper.deleteList(productImage_id_list)>0;
    }

    @Override
    public List<ProductImage> getList(Integer product_id, Byte productImage_type, PageUtil pageUtil) {
        return productImageMapper.select(product_id,productImage_type,pageUtil);
    }

    @Override
    public ProductImage get(Integer productImage_id) {
        return productImageMapper.selectOne(productImage_id);
    }

    @Override
    public Integer getTotal(Integer product_id, Byte productImage_type) {
        return productImageMapper.selectTotal(product_id,productImage_type);
    }
}
