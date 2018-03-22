package com.xq.tmall.service.impl;

import com.xq.tmall.dao.AddressMapper;
import com.xq.tmall.entity.Address;
import com.xq.tmall.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements AddressService{
    private AddressMapper addressMapper;
    @Resource(name = "addressMapper")
    public void setAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public boolean add(Address address) {
        return addressMapper.insertOne(address)>0;
    }

    @Override
    public boolean update(Address address) {
        return addressMapper.updateOne(address)>0;
    }

    @Override
    public List<Address> getList(String address_name, Integer address_regionId) {
        return addressMapper.select(address_name,address_regionId);
    }

    @Override
    public Address get(String address_areaId) {
        return addressMapper.selectOne(address_areaId);
    }

    @Override
    public List<Address> getRoot() {
        return addressMapper.selectRoot();
    }
}
