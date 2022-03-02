package com.zuo.xiaodi.interview.chapter2;

public class Test2TryCatchFinally {

    public static void main(String[] args) {
        System.out.println(test2());
    }

    public static int test1() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;

        } finally {
            a = 4;
        }
        return a;

    }

    public static int test2() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;

        } finally {
            a = 4;
            return a;
        }

    }

}
