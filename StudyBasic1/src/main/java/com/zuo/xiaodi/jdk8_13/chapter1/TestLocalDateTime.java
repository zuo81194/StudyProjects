package com.zuo.xiaodi.jdk8_13.chapter1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 测试Java8时间类，线程安全的
 */
public class TestLocalDateTime {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
//        System.out.println(now.getYear());
//        System.out.println(now.getMonth());
//        System.out.println(now.getMonthValue());
//        System.out.println(now.getDayOfYear());
//        System.out.println(now.getDayOfMonth());
//        System.out.println(now.getDayOfWeek());
        LocalDateTime date = LocalDateTime.of(2021, 12, 30,15,27);
        System.out.println(date);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String nowFormat = dtf.format(now);
        String dateFormat = dtf.format(date);
        System.out.println(nowFormat);
        System.out.println(dateFormat);

        System.out.println(Duration.between(date, now).toMillis());
    }
}
