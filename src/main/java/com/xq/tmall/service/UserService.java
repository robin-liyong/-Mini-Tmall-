package com.xq.tmall.service;

import com.xq.tmall.entity.User;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;

import java.util.List;

public interface UserService {
    boolean add(User user);
    boolean update(User user);

    List<User> getList(User user, OrderUtil orderUtil, PageUtil pageUtil);
    User get(Integer user_id);
    User login(String user_name, String user_password);
    Integer getTotal(User user);
}
