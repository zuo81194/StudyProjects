package com.zuo.basic.chapter5;

import com.zuo.basic.chapter4.Chair;

public class TestProtected1 extends Chair {

    public TestProtected1() {
        System.out.println("TestProtected1 constructor");
    }

    public static void main(String[] args) {

        TestProtected1 testProtected1 = new TestProtected1();

        //  !不同包中不能使用友好的方法   testProtected1.testNoneProtected1();
        testProtected1.testHasProtected2();
        Chair chair = new Chair();
        // 非同包不可使用友好的方法 ！ chair.testNoneProtected1();
        // 非同包非子类不可使用受保护的方法 ！chair.testHasProtected2();
    }

    public void change(){
        testHasProtected2();
    }

}
