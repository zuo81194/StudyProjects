package com.zuo.xiaodi.jdk8_13.chapter1;

public class Dog implements Animal{
    @Override
    public void run() {
        System.out.println("Dog running");
    }

    @Override
    public void eat() {
        System.out.println("Dog eating");
    }

    @Override
    public void breath() {
        System.out.println("Dog breathing");
    }
}
