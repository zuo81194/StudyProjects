package com.zuo.xiaodi.jdk8_13.chapter1;

import java.util.*;

public class TestOptional {

    public static void main(String[] args) {
//        Student stu = new Student("zuo", 17);
//        Optional<Student> student = Optional.of(stu);
//        Optional<Student> student1 = Optional.ofNullable(stu);
//        student1.get();
//        if (student1.isPresent()) {
//            System.out.println(student1.get());
//        } else {
//            System.out.println(student1.orElse(new Student("zuo", 18)));
//        }
//        System.out.println(student1.map(s -> s.age).orElse(18));


//        Thread thread = new Thread(() -> System.out.println("thread"));
//        thread.start();
//        System.out.println("main");

        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        for (String a : list) {
            System.out.println(a);
        }
    }

}
