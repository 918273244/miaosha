package com.miaosha.common.service;

import com.miaosha.common.domain.User;

public interface UserService {


    public User getById(int id);

    public boolean tx() ;


}
