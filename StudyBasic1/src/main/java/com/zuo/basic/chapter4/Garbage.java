package com.zuo.basic.chapter4;

public class Garbage {
    public static void main(String[] args) {
        //这里同包下可以访问访问表示为友好的类，default
        A a = new A();
        if (args.length == 0) {
            System.err.println("Usage: \n" +
                    "java Garbage before\n or:\n" +
                    "java Garbage after");
            return;
        }
        while (!Chair.f) {
            Chair chair = new Chair();
            new String("To take up space");
            //友好的和受保护的类在同包下其他类都能访问
            chair.testNoneProtected1();
            chair.testHasProtected2();
        }
        System.out.println(
                "After all Chairs have been created:\n" +
                        "total created = " + Chair.created +
                        ", total finalized = " + Chair.finalized);
        if (args[0].equals("before")) {
            System.out.println("gc():");
            System.gc();
            System.out.println("runFinalization():");
            System.runFinalization();
        }
        System.out.println("bye!");
        if (args[0].equals("after")) {
            System.runFinalizersOnExit(true);
        }
        int[][] a1 = {
                { 1, 2, 3, },
                { 4, 5, 6, },
        };
    }
}

