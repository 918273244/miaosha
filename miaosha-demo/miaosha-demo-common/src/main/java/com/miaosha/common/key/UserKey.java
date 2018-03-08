package com.miaosha.common.key;


import com.miaosha.common.redis.BasePrefix;

public class UserKey extends BasePrefix {

    private UserKey(String prefix){
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

}
