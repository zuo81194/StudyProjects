package com.zuo.xiaodi.jvm.chapter2;

//1.编译命令：javac User.java
//2.查看class文件命令：javap -l User.class
public class User {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}