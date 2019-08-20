package com.example.demo.redis.mode;

import lombok.Data;

import javax.persistence.*;

/**
 * @author panlihuan
 * @date 2019/8/20
 */
@Data
@Entity
@Table(name ="depa")
public class Depa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column( nullable = false)
    private String depaName;
    @Column( nullable = false)
    private String despector;
    @Column(nullable = false)
    private String title;
    private Long time;
}
