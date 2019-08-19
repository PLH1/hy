package com.example.demo.html2ppt;

import java.io.*;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.hslf.model.TextBox;

/**
 * @describe:
 * @outhor 潘立欢
 * @create 2019-07-12 11:09
 */
public class Html2PPt {
    public static void main(String[]args){
        try{
            File file = new File("D:\\data\\Hello.html");
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            DataInputStream dis = null;
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            String st="";
            while (dis.available() != 0) {
                st+=dis.readLine().toString()+"\n";
            }
            System.out.println(st);
            SlideShow slideShow = new SlideShow();
            Slide slide = slideShow.createSlide();
            TextBox txt = new TextBox();

            txt.setText(st+" ");
            slide.addShape(txt);
            FileOutputStream out = new FileOutputStream("D:\\data\\Hello.ppt");
            slideShow.write(out);
            out.close();
            bis.close();
            dis.close();
        }
        catch(Exception e){}
    }
}
