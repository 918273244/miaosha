package com.miaosha.common.key;

import com.miaosha.common.redis.BasePrefix;

public class AccessKey extends BasePrefix {
    public AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }
}
