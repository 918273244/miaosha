package com.miaosha.common.service.impl;

import com.miaosha.common.dao.UserDao;
import com.miaosha.common.domain.User;
import com.miaosha.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public boolean tx() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("111");
        userDao.insert(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setName("2222");
        userDao.insert(user2);
        return true;
    }


}
