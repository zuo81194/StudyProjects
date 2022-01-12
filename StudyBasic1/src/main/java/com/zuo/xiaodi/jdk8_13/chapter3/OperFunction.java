package com.zuo.xiaodi.jdk8_13.chapter3;

@FunctionalInterface
public interface OperFunction<R, T> {

    R operator(T t1, T t2);
}
