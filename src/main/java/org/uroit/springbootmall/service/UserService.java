package org.uroit.springbootmall.service;

import org.uroit.springbootmall.dto.UserLoginRequest;
import org.uroit.springbootmall.dto.UserRegisterRequest;
import org.uroit.springbootmall.model.User;

public interface UserService {

    public Integer register(UserRegisterRequest userRegisterRequest);

    public User getUserById(Integer userId);

    public User login(UserLoginRequest userLoginRequest);
}
