package com.xq.tmall.service.impl;

import com.xq.tmall.dao.ProductMapper;
import com.xq.tmall.entity.Product;
import com.xq.tmall.service.ProductService;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;
    @Resource(name = "productMapper")
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public boolean add(Product product) {
        return productMapper.insertOne(product)>0;
    }

    @Override
    public boolean update(Product product) {
        return productMapper.updateOne(product)>0;
    }

    @Override
    public List<Product> getList(Product product, Byte[] product_isEnabled_array,OrderUtil orderUtil, PageUtil pageUtil) {
        return productMapper.select(product, product_isEnabled_array, orderUtil, pageUtil);
    }

    @Override
    public Product get(Integer product_Id) {
        return productMapper.selectOne(product_Id);
    }

    @Override
    public Integer getTotal(Product product,Byte[] product_isEnabled_array) {
        return productMapper.selectTotal(product,product_isEnabled_array);
    }
}
