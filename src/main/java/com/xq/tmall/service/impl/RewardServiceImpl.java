package com.xq.tmall.service.impl;

import com.xq.tmall.dao.RewardMapper;
import com.xq.tmall.entity.Reward;
import com.xq.tmall.service.RewardService;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("rewardService")
public class RewardServiceImpl implements RewardService {
    private RewardMapper rewardMapper;
    @Resource(name = "rewardMapper")
    public void setRewardMapper(RewardMapper rewardMapper) {
        this.rewardMapper = rewardMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Reward reward) {
        return rewardMapper.insertOne(reward)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Reward reward) {
        return rewardMapper.updateOne(reward)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] reward_id_list) {
        return rewardMapper.deleteList(reward_id_list)>0;
    }

    @Override
    public List<Reward> getList(Reward reward, Byte[] reward_isEnabled_array, OrderUtil orderUtil, PageUtil pageUtil) {
        return rewardMapper.select(reward,reward_isEnabled_array,orderUtil,pageUtil);
    }

    @Override
    public List<Reward> getListByUserId(Integer user_id) {
        return rewardMapper.selectByUserId(user_id);
    }

    @Override
    public Reward get(Integer reward_id) {
        return rewardMapper.selectOne(reward_id);
    }

    @Override
    public Integer getTotal(Reward reward, Byte[] reward_isEnabled_array) {
        return rewardMapper.selectTotal(reward,reward_isEnabled_array);
    }
}
