package com.example.demo.learn;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author panlihuan
 * @date 2019/8/7
 */
public class HelloWord {


    public static void main(String[] args) {
        BeanFactory beanFactory=new XmlBeanFactory(new ClassPathResource("test.xml"));
    }
}
