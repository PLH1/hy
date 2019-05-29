package com.example.hy.hy.dao;

import com.example.hy.hy.daomain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @describe:
 * @outhor 潘立欢
 * @create 2019-05-29 15:15
 */
@Mapper
public interface UserDao {
    /**
     * 获得所有用户
     * @return User
     */
    public User userAll();
}
