package com.xq.tmall.dao;

import com.xq.tmall.entity.Reward;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RewardMapper {
    Integer insertOne(@Param("reward") Reward reward);
    Integer updateOne(@Param("reward") Reward reward);
    Integer deleteList(@Param("reward_id_list") Integer[] reward_id_list);

    List<Reward> select(@Param("reward") Reward reward, @Param("reward_isEnabled_array") Byte[] reward_isEnabled_array,@Param("orderUtil") OrderUtil orderUtil,@Param("pageUtil") PageUtil pageUtil);
    List<Reward> selectByUserId(@Param("user_id") Integer user_id);

    Reward selectOne(@Param("reward_id") Integer reward_id);
    Integer selectTotal(@Param("reward") Reward reward,@Param("reward_isEnabled_array") Byte[] reward_isEnabled_array);
}
