package com.ylb.Pojo;

public class BlogTag {
    private Long bid;
    private Long tid;

    public BlogTag() {
    }

    public BlogTag(Long bid, Long tid) {
        this.bid = bid;
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "BlogTag{" +
                "bid=" + bid +
                ", tid=" + tid +
                '}';
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }
}
