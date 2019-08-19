package com.example.demo.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panlihuan
 * @date 2019/8/19
 */
@RestController
public class HelloWord {
    @GetMapping("login")
    public Object object() {
        return "hello word";
    }
}
