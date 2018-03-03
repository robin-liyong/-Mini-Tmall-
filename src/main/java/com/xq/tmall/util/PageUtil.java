package com.xq.tmall.util;

public final class PageUtil {
    private Integer index;
    private Integer count;
    private Integer total;

    public Boolean isHasPrev(){
        return index>1;
    }

    public Boolean isHasNext(){
        return index<getTotalPage();
    }

    public Integer getTotalPage(){
        return (int)Math.ceil((double)(total/count));
    }

    public PageUtil(){

    }

    public PageUtil(Integer index, Integer count) {
        this.index = index-1;
        this.count = count;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index-1;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
