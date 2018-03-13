package com.miaosha.common.key;

import com.miaosha.common.redis.BasePrefix;

public class MiaoshaKey extends BasePrefix {

    private MiaoshaKey(String prefix) {
        super(prefix);
    }
    public static MiaoshaKey isGoodsOver = new MiaoshaKey("go");

}
