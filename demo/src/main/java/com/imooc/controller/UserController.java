package com.imooc.controller;

import com.imooc.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public User querUserDetail(@PathVariable Integer id) {
        System.out.println("id:" + id);
        User user = new User();
        user.setId(1);
        user.setUsername("chongchong");
        user.setPassword("123456");
        return user;
    }
}
