package com.zuo.xiaodi.interview.chapter2;

/**
 * 任意两个非零整数做交换的几种解决方案
 */
public class Test1Swap {

    public static void main(String[] args) {
        swap3(31, 40);
    }

    //这个是巧思
    private static void swap1(int a, int b) {
        System.out.printf("a=%d,b=%d", a, b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.printf("\na=%d,b=%d", a, b);
    }

    //这个是面试官最想要的答案
    private static void swap2(int a, int b) {
        System.out.printf("a=%d,b=%d", a, b);
        a = a ^ b;
        System.out.printf("\n1.a=%d,b=%d", a, b);
        b = a ^ b;
        System.out.printf("\n2.a=%d,b=%d", a, b);
        a = a ^ b;
        System.out.printf("\n3.a=%d,b=%d", a, b);
    }

    //这个开销最大，还要多定义一个变量
    public static void swap3(int a, int b) {
        System.out.printf("a=%d,b=%d", a, b);
        int c = a;
        a = b;
        b = c;
        System.out.printf("\na=%d,b=%d", a, b);
    }

}
