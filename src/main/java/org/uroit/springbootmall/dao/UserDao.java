package org.uroit.springbootmall.dao;

import org.uroit.springbootmall.dto.UserRegisterRequest;
import org.uroit.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
