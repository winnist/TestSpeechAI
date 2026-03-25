package org.uroit.springbootmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uroit.springbootmall.dao.UserDao;
import org.uroit.springbootmall.dto.UserRegisterRequest;
import org.uroit.springbootmall.model.User;
import org.uroit.springbootmall.service.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Integer reqister(UserRegisterRequest userRegisterRequest){
        return userDao.createUser(userRegisterRequest);
    }

    public User getUserById(Integer userId){
        return userDao.getUserById(userId);
    }
}
