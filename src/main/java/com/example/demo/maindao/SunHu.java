package com.example.demo.maindao;

import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author panlihuan
 * @date 2019/8/8
 */
@Data
public class SunHu implements BeanNameAware {
    private int product;
    private String zuozhe;

    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }
}
