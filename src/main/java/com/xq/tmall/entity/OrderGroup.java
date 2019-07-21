package com.xq.tmall.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 订单组类
 * @implNote 该类用于辅助后台图表的生成，亦不属于实体类
 * @author 贤趣项目小组
 */
public class OrderGroup {
    private Date productOrder_pay_date/*订单组的支付日期*/;

    private Integer productOrder_count/*订单组的统计个数*/;

    private Byte productOrder_status/*订单组的订单状态*/;

    public String getProductOrder_pay_date() {
        if (productOrder_pay_date != null) {
            SimpleDateFormat time = new SimpleDateFormat("MM/dd", Locale.UK);
            return time.format(productOrder_pay_date);
        }
        return null;
    }

    public void setProductOrder_pay_date(Date productOrder_pay_date) {
        this.productOrder_pay_date = productOrder_pay_date;
    }

    public Integer getProductOrder_count() {
        return productOrder_count;
    }

    public void setProductOrder_count(Integer productOrder_count) {
        this.productOrder_count = productOrder_count;
    }

    public Byte getProductOrder_status() {
        return productOrder_status;
    }

    public void setProductOrder_status(Byte productOrder_status) {
        this.productOrder_status = productOrder_status;
    }

    @Override
    public String toString() {
        return "OrderGroup{" +
                "productOrder_pay_date=" + productOrder_pay_date +
                ", productOrder_count=" + productOrder_count +
                ", productOrder_status=" + productOrder_status +
                '}';
    }
}
