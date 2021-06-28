package com.zuo.basic.view.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile三大特性
 * 1. 保证可见性
 * 2.不保证原子性
 * 3.禁止指令重排
 */

/**
 * done 2~4课程讲解volatile特性一:保证可见性
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
class MyData2 {//MyData2.java --> MyData2.class --> JVM字节码
    //共享变量
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

}

/**
 * done 5~7课程验证volatile特性二：不保证原子性
 * 为什么number++在多线程的情况下会出现这种现象
 * 原理：由于number++底层的操作是由三步完成的（1.获得初始值，2.加1操作，3.把累加的值写回），多线程情况下可能会被加塞，值改变了也没来得及通知子线程，出现丢失写值的情况
 * 解决方案：1.加sync
 * 2.使用juc下的AtomicInteger（底层原理：CAS-->compare and swap）
 */
class Test3Volatile {
    public static void main(String[] args) {
        MyData2 myData2 = new MyData2();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    //不保证原子性
                    myData2.addPlusPlus();
                    //保证原子性的解决方案(使用AtomicInteger)
                    myData2.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t int type finally number value: " + myData2.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type finally number value: " + myData2.atomicInteger);
    }

    public static void seeOkVolatile() {

    }
}

/**
 * todo volatile指令重排案例1、2
 */