package org.uroit.springbootmall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.uroit.springbootmall.dao.UserDao;
import org.uroit.springbootmall.dto.UserLoginRequest;
import org.uroit.springbootmall.dto.UserRegisterRequest;
import org.uroit.springbootmall.model.User;
import org.uroit.springbootmall.service.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public Integer reqister(UserRegisterRequest userRegisterRequest){

        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if (user!=null){
            log.warn("該email{}已經被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return userDao.createUser(userRegisterRequest);
    }

    public User getUserById(Integer userId){
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if(user == null){
            log.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
        }

        if(user.getPassword().equals(userLoginRequest.getPassword())){
            return user;
        }else{
            log.warn("email {}的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}
