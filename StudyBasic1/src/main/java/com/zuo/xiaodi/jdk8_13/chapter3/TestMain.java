package com.zuo.xiaodi.jdk8_13.chapter3;

import com.zuo.xiaodi.jdk8_13.chapter1.Student;

import java.util.*;
import java.util.stream.Collectors;

public class TestMain {


    public static void main(String[] args) {

        //Optional类
        Student stu = new Student("zuo",6);
        Integer year = Optional.ofNullable(stu).map(Student::getAge).orElse(7);
        System.out.println("year: " + year);

        //1.创建线程的区别
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();

        //函数式编程更加简洁
//        new Thread(() -> System.out.println("")).start();

        //2.字符串排序区别
//        List<String> list = Arrays.asList("aaa", "eee", "rrr");
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });

//        Collections.sort(list, (a, b) -> b.compareTo(a));
//
//        for (String string : list) {
//            System.out.println(string);
//        }

        //3.自定义函数使用
//        System.out.println(operater(99, 34, Integer::sum));
//        System.out.println(operater(99, 34, (Integer x, Integer y) -> {
//            return x - y;
//        }));
//        System.out.println(operater(99, 34, (x, y) -> x * y));
//        System.out.println(operater(99, 34, (x, y) -> x / y));

//        Student student = new Student();
//        Consumer<Student> consumer = (s) -> s.setAge(12);
//        consumer.accept(student);
//        System.out.println(student.getAge());
//        Predicate<Student> aa = Objects::nonNull;
//        //lamda表达式的另一种表现 ::
//        BiFunction<Integer, Integer, Integer> biFunction = Integer::sum;
//        Integer i1 = 130;
//        Integer i2 = 131;
//        Student student1 = Student.newInstance(Student::new);
//        Student student2 = Student.newInstance(() -> new Student("zuo1", 17));

        // 构造函数引⽤，多个参数
//        BiFunction<String, Integer, Student> biFunction1 = Student::new;
//        Student user1 = biFunction1.apply("⼩滴课堂", 28);
//        System.out.println(user1.toString());

//        ArrayList<Student> students = new ArrayList<>();
//        Stream<Student> studentStream = students.stream();
//        Stream<Student> parallelStream = students.parallelStream();

//        List<String> stringList = Arrays.asList("springboot教程", "微服务教程", "并发编程", "压力测试", "架构课程");
        //可以用于DO到DVO的转化
//        List<String> collect = stringList.stream().map(obj -> "在⼩滴课堂学：" + obj).collect(Collectors.toList());
//        String str = stringList.stream().reduce((a, b) -> a + b).get();
//        System.out.println(collect);

//        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //一定按照顺序打印
//        integerList.stream().forEach(System.out::print);
//        System.out.println();
        //因为是并行的，所以不一定按照顺序输出，当元素比较少的时候不一定比串行stream快
//        integerList.parallelStream().forEach(System.out::print);

        //将成员一定操作后返回一个值
//        Integer integer = Stream.of(3, 5, 6, 8).reduce(Integer::sum).get();
//        System.out.println(integer);

        //在集合()中添加指定的连接符、前缀、后缀并返回字符串
//        List<String> abcStrs = Arrays.asList("a", "b", "c");
//        String collect = abcStrs.stream().collect(Collectors.joining(",", "[", "]"));
//        System.out.println(collect);

        //分组1：符合条件的为一组，不符合条件的是另一组
//        List<String> stringList = Arrays.asList("springboot", "vue", "double", "doker", "jsp");
//        Map<Boolean, List<String>> booleanListMap = stringList.stream().collect(Collectors.partitioningBy(obj -> obj.length() >= 4));
//        System.out.println(booleanListMap);

        //分组2：根据指定条件分组，比如：以下以年龄分组
        List<Student> students = Arrays.asList(new Student("zuo", 18), new Student("zhang", 19),
                new Student("jun", 19), new Student("hong", 19),
                new Student("meng", 20), new Student("hui", 20),
                new Student("hao", 18));
        Map<Integer, List<Student>> studentMapByAge = students.stream().collect(Collectors.groupingBy(Student::getAge));
        System.out.println(studentMapByAge);

        //分组3：求不同年龄段的人数
        Map<Integer, Long> studentNum = students.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));
        System.out.println(studentNum);

        //分组4：可以求出各种统计
        IntSummaryStatistics statistics = students.stream().collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("平均值：" + statistics.getAverage());
        System.out.println("⼈数：" + statistics.getCount());
        System.out.println("最⼤值：" + statistics.getMax());
        System.out.println("最⼩值：" + statistics.getMin());
        System.out.println("总和：" + statistics.getSum());

    }

    public static Integer operater(Integer x, Integer y, OperFunction<Integer, Integer> of) {
        return of.operator(x, y);
    }

}
