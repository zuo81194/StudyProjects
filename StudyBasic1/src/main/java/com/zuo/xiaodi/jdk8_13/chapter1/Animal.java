package com.zuo.xiaodi.jdk8_13.chapter1;

public interface Animal {

    void run();
    void eat();
    //不必被实现类实现
    default void breath(){
        System.out.println("breathing");
    }

    //可通过类名.方法()调用
    static void talk(){
        System.out.println("I can talk");
    }

}
