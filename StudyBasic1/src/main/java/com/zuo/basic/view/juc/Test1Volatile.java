package com.zuo.basic.view.juc;

import java.util.concurrent.TimeUnit;

/**
 * volatile三大特性
 * 1. 保证可见性
 * 2.不保证原子性
 * 3.禁止指令重排
 */

/**
 * 2~4课程讲解volatile特性一保证可见性
 * zlp studies on 2021/6/20
 */
public class Test1Volatile {

    /**
     * 1   验证volatile的可见性
     * 1.1  加入int number = 0; number 变量之前并没有添加volatile关键字修饰
     */
    public static void main(String[] args) {
        MyData1 myData = new MyData1();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + "\t update number value" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        //第二个线程，main线程
        while (myData.number == 0) {
            //main线程一直停在这里等待循环，直到number的值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }

}

class Test2Volatile {

    /**
     * 1   验证volatile的可见性
     * 1.1  加入int number = 0; number 变量之前并没有添加volatile关键字修饰
     */
    public static void main(String[] args) {
        MyData2 myData = new MyData2();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + "\t update number value" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        //第二个线程，main线程
        while (myData.number == 0) {
            //main线程一直停在这里等待循环，知道number的值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }

}

/**
 * number 变量之前并没有添加volatile关键字修饰
 */
class MyData1 {
    //共享变量
    int number = 0;

    public void addTo60() {
        this.number = 60;
    }

}

/**
 * number 变量添加volatile关键字修饰
 */
class MyData2 {
    //共享变量
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

}

/**
 * todo 5~7课程讲解volatile特性一保证可见性
 */
