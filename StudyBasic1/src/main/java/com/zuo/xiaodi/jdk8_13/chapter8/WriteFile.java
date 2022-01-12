package com.zuo.xiaodi.jdk8_13.chapter8;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteFile {

    public static void main(String[] args) {
        String path = "C:\\Users\\zuo\\Desktop\\test.txt";
//        testOld(path);
        testNew(path);
    }

    /**
     * 在filePath的文件中写入数据(旧的方法)
     *
     * @param filePath 文件全路径
     */
    public static void testOld(String filePath) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
            outputStream.write((filePath + "左写入的内容").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在filePath的文件中写入数据(新的方法)
     *
     * @param filePath 文件全路径
     */
    public static void testNew(String filePath) {//不需要再手动关闭流
        try (OutputStream outputStream = new FileOutputStream(filePath)) {//可以加分号';'创建多个流对象
            outputStream.write((filePath + "：右写入的内容").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
