package com.zuo.basic.chapter2;

import java.util.Date;
import java.util.Properties;

/**
 * 该类的作用就是测试静态方法的应用
 */
public class Property {

    /**
     * 计算传入字符串长度的二倍
     *
     * @param str 字符串
     * @return 字符串长度的二倍
     */
    public int test(String str) {
        return str.length() * 2;
    }

    /**
     * 我是主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        System.out.println(new Date());
        Properties p = System.getProperties();
        p.list(System.out);
        System.out.println("--- Memory Usage:");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total Memory = "
                + rt.totalMemory()
                + " Free Memory = "
                + rt.freeMemory());

        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
