package com.it4alla.idempotent.controller;

import java.util.concurrent.TimeUnit;

import com.it4alla.idempotent.annotation.Idempotent;
import com.it4alla.idempotent.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ITyunqing
 * @email 1186355422@qq.com
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @Idempotent(isIdempotent = true,expireTime = 3,timeUnit = TimeUnit.SECONDS,info = "请勿重复添加用户",delKey = false)
    @GetMapping(value = "add")
    public String add(User user, String love, Integer count){
        userServiceImpl.add(user);
        return "添加成功";
    }

    @RequestMapping(value ="test")
    public String  test(String name){
        return name;
    }
}
