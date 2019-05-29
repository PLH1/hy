package com.example.hy.hy.Controller;

import com.example.hy.hy.dao.UserDao;
import com.example.hy.hy.daomain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @outhor 潘立欢
 * @create 2019-05-29 15:17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/query")
    public User testQuery() {
        return userDao.userAll();
    }
}
