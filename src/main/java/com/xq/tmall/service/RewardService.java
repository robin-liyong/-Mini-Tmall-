package com.xq.tmall.service;

import com.xq.tmall.entity.Reward;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;

import java.util.List;

public interface RewardService {
    boolean add(Reward reward);
    boolean update(Reward reward);
    boolean deleteList(Integer[] reward_id_list);

    List<Reward> getList(Reward reward, Byte[] reward_isEnabled_array, OrderUtil orderUtil, PageUtil pageUtil);
    List<Reward> getListByUserId(Integer user_id);

    Reward get(Integer reward_id);
    Integer getTotal(Reward reward, Byte[] reward_isEnabled_array);
}
