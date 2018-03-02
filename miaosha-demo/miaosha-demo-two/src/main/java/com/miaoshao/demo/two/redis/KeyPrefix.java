package com.miaoshao.demo.two.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
