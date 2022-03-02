package com.zuo.xiaodi.interview.chapter2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * JDK7之后的写法，JDK9⼜进⾏了改良，但是变化不⼤，记住下⾯的写法即可
 * 需要关闭的资源只要实现了java.lang.AutoCloseable，就可以⾃动被关闭
 * try()⾥⾯可以定义多个资源，它们的关闭顺序是最后在try()定义的资源先关闭
 */
public class Test3IO {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\zuo\\Desktop\\new 1.txt");
             BufferedInputStream bis = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream("C:\\Users\\zuo\\Desktop\\copy.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)
        ) {
            int size;
            byte[] bytes = new byte[1024];
//            while ((size = bis.read(bytes)) != -1) {
//                bos.write(bytes, 0, size);
//            }
            while ((size = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
