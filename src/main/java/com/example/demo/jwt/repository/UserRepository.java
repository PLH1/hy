package com.example.demo.jwt.repository;

import com.example.demo.jwt.mode.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @author panlihuan
 * @date 2019/8/19
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);

    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
