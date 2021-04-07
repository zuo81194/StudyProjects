package com.zuo.basic.interview;

import java.util.concurrent.TimeUnit;

/**
 * Create by zlp on 2021/3/17
 * <p>
 * 饿汉模式（线程安全）,测试成功
 */
public class FirsrSingleton {

    public static FirsrSingleton firsrSingleton = new FirsrSingleton();

    private FirsrSingleton() {
        System.out.println("FirsrSingleton created");
    }

}
//第二种饿汉
class TwoSignleton{
    public static TwoSignleton twoSignleton;

    private TwoSignleton() {
        System.out.println("TwoSignleton created");
    }

    public static TwoSignleton getInstance(){
        return new TwoSignleton();
    }
}

class TestSingleton1 {
    public static void main(String[] args) {
        System.out.println("程序开始");
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FirsrSingleton firsrSingleton = FirsrSingleton.firsrSingleton;
                    System.out.println(Thread.currentThread().getName() + "线程获得单例对象");
                }
            }, i + "").start();
        }
        try {
            //这里的作用是给子线程充分的时间启动起来
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序结束");
    }
}

class TestSingleton2 {
    public static void main(String[] args) {
        System.out.println("程序开始");
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TwoSignleton twoSignleton = TwoSignleton.getInstance();
                    System.out.println(Thread.currentThread().getName() + "线程获得单例对象");
                }
            }, i + "").start();
        }
        try {
            //这里的作用是给子线程充分的时间启动起来
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序结束");
    }
}