package com.miaosha.common.key;

import com.miaosha.common.redis.BasePrefix;

public class OrderKey extends BasePrefix {

    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
