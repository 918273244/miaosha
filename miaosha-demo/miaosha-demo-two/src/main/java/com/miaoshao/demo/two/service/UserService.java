package com.miaoshao.demo.two.service;

import com.miaoshao.demo.two.domain.User;

public interface UserService {


    public User getById(int id);

    public boolean tx() ;


}
