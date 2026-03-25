package org.uroit.springbootmall.service;

import org.uroit.springbootmall.dto.UserRegisterRequest;
import org.uroit.springbootmall.model.User;

public interface UserService {

    public Integer reqister(UserRegisterRequest userRegisterRequest);

    public User getUserById(Integer userId);
}
