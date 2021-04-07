package com.zuo.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        String currentTime = format.format(new Date());
        System.out.println("程序已经启动！------------" + currentTime + "------------");
    }
}
