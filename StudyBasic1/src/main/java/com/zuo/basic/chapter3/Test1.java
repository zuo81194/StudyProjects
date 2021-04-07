package com.zuo.basic.chapter3;

public class Test1 {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i == 47) {
                return;
//                break;
            }
            System.out.println(i);
        }
    }
}
