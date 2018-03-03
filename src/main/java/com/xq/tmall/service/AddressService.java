package com.xq.tmall.service;

import com.xq.tmall.entity.Address;

import java.util.List;

public interface AddressService {
    boolean add(Address address);
    boolean update(Address address);

    List<Address> getList(String address_name, Integer address_regionId);
    Address get(Integer address_areaId);
    List<Address> getRoot();
}
