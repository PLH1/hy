package com.example.demo.maindao;

import lombok.Data;

import java.io.Serializable;


/**
 * @author panlihuan
 * @date 2019/8/1
 */
@Data
public class Book  implements Serializable{

    private static final long serialVersionUID = 5896173211323153008L;
    private String Name;
    private Integer age;

    public Book(String name, Integer age) {
        Name = name;
        this.age = age;
    }

    public Book() {
    }

}
