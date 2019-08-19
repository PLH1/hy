package com.example.demo;

import com.example.demo.maindao.Book;
import com.sun.corba.se.pept.encoding.OutputObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() throws Exception{
        Book book = new Book("a",1);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("book.txt"));
        objectOutputStream.writeObject(book);
        objectOutputStream.close();
    }

    @Test
    public void contextRead() throws Exception{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("book.txt"));
        Book book = (Book) objectInputStream.readObject();
        System.out.println(book);
    }
    @Test
    public void contextRead2() throws Exception{
        File file=new File("book.txt");
        FileReader fileReader = new FileReader(file);
        int data = fileReader.read();
        while (data != -1){
            System.out.print((char)data);
            data = fileReader.read();
        }
        fileReader.close();
    }

}
