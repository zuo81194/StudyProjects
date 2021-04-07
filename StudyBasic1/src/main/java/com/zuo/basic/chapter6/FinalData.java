package com.zuo.basic.chapter6;

import java.util.Hashtable;
import java.util.Vector;

class Value {
    int i = 1;
}

public class FinalData {

    final int i1 = 1;
    final static int i2 = 2;
    public static final int i3 = 39;
    // Cannot be compile-time constants:
    final int i4 = (int) (Math.random() * 20);
    static final int i5 = (int) (Math.random() * 20);

    Value v1 = new Value();
    final Value v2 = new Value();
    static final Value v3 = new Value();
    //! final Value v4; // Pre-Java 1.1 Error:
    // no initializer
    // Arrays:
    final int[] a = {1, 2, 3, 4, 5, 6};

    public void print(String id) {
        System.out.println(
                id + ": " + "i4 = " + i4 +
                        ", i5 = " + i5);
    }

    public static void main(String[] args) {
        FinalData test1 = new FinalData();
        // ！ test1.i1++; //value can't change
        // ！test1.i2; //不能通过实例访问静态成员
        // ！test1.i3; //不能通过实例访问静态成员
        // ！ test1.i4++; //value can't change
        // ！test1.i5; //不能通过实例访问静态成员

        test1.v1 = new Value();


        for (int i = 0; i < test1.a.length; i++) {
            test1.a[i]++;
        }

        // ! test1.v2 = new Value(); //Error: Can't
        // ! test1.v3 = new Value(); //Error: Can't
        // ! test1.a = new int[3];
    }
}

 class aaa {
    int i = 0;
    final int j =1;
    static int zuo;
    static{
        zuo = 5;
    }
    final void f(){}
    static void g(){}

     public aaa() {
         // ! this.zuo = 5;
     }
 }

class bbb extends aaa{
    // ! void  f(){} //不能被子类覆盖
    // ! void g(){} //不能被子类覆盖
    //Vector 堆栈Stack就是
    //Hashtable


}
