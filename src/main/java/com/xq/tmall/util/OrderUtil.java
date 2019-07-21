package com.xq.tmall.util;

/**
 * 查询排序工具
 * @author 贤趣项目小组
 */
public final class OrderUtil {
    //排序字段
    private String orderBy;
    //倒序排序
    private boolean isDesc;

    public OrderUtil(String orderBy){
        this.orderBy = orderBy;
        this.isDesc = false;
    }

    public OrderUtil(String orderBy,boolean isDesc) {
        this.orderBy = orderBy;
        this.isDesc = isDesc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public boolean getIsDesc() {
        return isDesc;
    }
}
