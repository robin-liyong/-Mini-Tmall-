package com.xq.tmall.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 打赏实体类
 * @author 贤趣项目小组
 */
public class Reward {
    private Integer reward_id/*打赏ID*/;

    private String reward_name/*打赏昵称*/;

    private String reward_content/*打赏备注*/;

    private Date reward_createDate/*打赏时间*/;

    private Integer reward_state/*打赏状态*/;

    private Double reward_amount;/*打赏金额*/;

    private User reward_user/*打赏用户*/;

    //数据查询
    private Double reward_lowest_amount;/*打赏最低金额*/;

    @Override
    public String toString() {
        return "Reward{" +
                "reward_id=" + reward_id +
                ", reward_name='" + reward_name + '\'' +
                ", reward_content='" + reward_content + '\'' +
                ", reward_createDate=" + reward_createDate +
                ", reward_user=" + reward_user +
                ", reward_state=" + reward_state +
                ", reward_amount=" + reward_amount +
                '}';
    }

    public Reward(){

    }

    public Reward(Integer reward_id, String reward_name, String reward_content, Date reward_createDate, Integer reward_state, Double reward_amount, User reward_user, Double reward_lowest_amount) {
        this.reward_id = reward_id;
        this.reward_name = reward_name;
        this.reward_content = reward_content;
        this.reward_createDate = reward_createDate;
        this.reward_state = reward_state;
        this.reward_amount = reward_amount;
        this.reward_user = reward_user;
        this.reward_lowest_amount = reward_lowest_amount;
    }

    public Integer getReward_id() {
        return reward_id;
    }

    public Reward setReward_id(Integer reward_id) {
        this.reward_id = reward_id;
        return this;
    }

    public String getReward_name() {
        return reward_name;
    }

    public Reward setReward_name(String reward_name) {
        this.reward_name = reward_name;
        return this;
    }

    public String getReward_content() {
        return reward_content;
    }

    public Reward setReward_content(String reward_content) {
        this.reward_content = reward_content;
        return this;
    }

    public String getReward_createDate() {
        if(reward_createDate != null){
            SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            return time.format(reward_createDate);
        }
        return null;
    }

    public Reward setReward_createDate(Date reward_createDate) {
        this.reward_createDate = reward_createDate;
        return this;
    }

    public User getReward_user() {
        return reward_user;
    }

    public Reward setReward_user(User reward_user) {
        this.reward_user = reward_user;
        return this;
    }

    public Integer getReward_state() {
        return reward_state;
    }

    public Reward setReward_state(Integer reward_state) {
        this.reward_state = reward_state;
        return this;
    }

    public Double getReward_amount() {
        return reward_amount;
    }

    public Reward setReward_amount(Double reward_amount) {
        this.reward_amount = reward_amount;
        return this;
    }

    public Double getReward_lowest_amount() {
        return reward_lowest_amount;
    }

    public Reward setReward_lowest_amount(Double reward_lowest_amount) {
        this.reward_lowest_amount = reward_lowest_amount;
        return this;
    }
}
