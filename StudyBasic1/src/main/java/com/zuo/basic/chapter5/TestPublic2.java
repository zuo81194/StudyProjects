package com.zuo.basic.chapter5;

import com.zuo.basic.chapter4.Chair;

public class TestPublic2 {

    public TestPublic2() {
        System.out.println("TestPublic2 be created");
    }

    public static void main(String[] args) {
        //可访问同包下public的类
        TestPublic1 testPublic1 = new TestPublic1();
        // 这里不可访问不同包下chapter4中的访问标识为友好的A类，故编译出错     A a = new A();
        //可以访问不同包下的public的类
        new Chair();
    }
}
