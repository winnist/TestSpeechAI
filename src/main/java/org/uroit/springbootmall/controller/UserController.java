package org.uroit.springbootmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.uroit.springbootmall.dto.UserLoginRequest;
import org.uroit.springbootmall.dto.UserRegisterRequest;
import org.uroit.springbootmall.model.User;
import org.uroit.springbootmall.service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/users/register")
    public ResponseEntity<User>reqister(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.reqister(userRegisterRequest);
        User user = userService.getUserById(userId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user =  userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
