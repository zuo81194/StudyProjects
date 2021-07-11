package com.zuo.basic.view.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * done 第20课 集合不安全
 */
public class Test2 {
    //集合类不安全的纹理
    //ArrayList
    public static void main(String[] args) {
        new ArrayList<Integer>().add(1);//底层是数组，且初始容量是10
//        List<String> list = new ArrayList<>();
        List<String> list1 = Arrays.asList("a", "b", "c");
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        list1.forEach(System.out::println);
        //ArrayList线程不安全
        //case:
        for (int i = 0; i < 100; i++) {//出现CurrentModificationException的错误
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        /**
         * 1.故障现象
         *      java.util.ConcurrentModificationException
         * 2.导致原因
         *      没有加锁
         * 3.解决方案
         *      1>Vector不建议
         *      2>Collections.synchronizedList(new ArrayList<>())
         *      3>new CopyOnWriteArrayList<>() 写时复制
         * 4.优化建议(同样的错误不犯第二次)
         */

    }
}

/**
 * done 第21课：CopyOnWriteArrayList源码: 读写分离思想
 */
class testCopyOnWriteArrayList {

    public static void main(String[] args) {
        //CopyOnWriteArray add()方法的源码
        /*synchronized (lock) {
            Object[] es = getArray();
            int len = es.length;
            es = Arrays.copyOf(es, len + 1);//复制旧list，然后扩容+1
            es[len] = e;
            setArray(es);
            return true;
        }*/
    }
}

/**
 * done 22: 集合类不安全之Set及解决
 * 其他：HashSet底层：HashMap
 */
class ContainerNotSafeDemo {

    public static void main(String[] args) {
//        HashSet<Object> sets = new HashSet<>();
//        Set<Object> sets = Collections.synchronizedSet(new HashSet<>());
        Set<Object> sets = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                sets.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(sets);
            }, String.valueOf(i)).start();
            new HashSet<>();
            //hashset add的是hashMap中的key，value=Object是恒定的
        }
    }
}

/**
 * todo 23: 集合类不安全之Map
 */
class ContainerNotSafeMapDemo {
    public static void main(String[] args) {
//        HashMap<Object, Object> map = new HashMap<>();
//        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
        Map<Object, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
            new HashSet<>();
            //hashset add的是hashMap中的key，value=Object是恒定的
        }
    }
}

class Person {
    int age;
    String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * done 24 TransferValue 醒脑小练习
 */
class TransferValue {

    public void changeAge(int age) {
        age = 30;
    }

    public void changeStr(String str) {
        str = "xxx";
    }

    public void changeObject(Person person) {
        person.setName("xxx");
    }

    public static void main(String[] args) {
        TransferValue test = new TransferValue();
        int age = 20;
        test.changeAge(age);
        System.out.println("age:" + age);
        Person person = new Person("abc");
        test.changeObject(person);
        System.out.println("name:" + person.getName());
        String str = "abc";
        test.changeStr(str);
        System.out.println("String:" + str);
    }
}

/**
 * done 25: java锁之公平和非公平锁
 */
class T1 {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();//默认 非公平锁：并不是按照先后顺序，上来就直接占有锁，若尝试失败则采用公平锁，有可能造成优先级反转或者饥饿现象
        Lock lock = new ReentrantLock(true);//公平锁：按照顺序获取锁，先来后到
        //非公平锁的吞吐量比公平锁大
        //对于Synchronized而言，也是一种非公平锁
    }
}

/**
 * done 26:java锁之可重入锁和递归锁理论知识
 * 可重入锁又名递归锁：线程可进入任何一个他已经拥有锁的所同步着的代码块，外层获取锁后，内层的锁自动获取
 * ReentrantLock、Synchronized就是典型的可重入锁
 * 重入锁：避免死锁
 */
class ReenteerLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T1").start();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T2").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        Phone2 phone2 = new Phone2();
        Thread t3 = new Thread(phone2);
        Thread t4 = new Thread(phone2);
        t3.start();
        t4.start();
    }
}

class Phone {
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail");
    }
}

class Phone2 implements Runnable {
    public Lock lock = new ReentrantLock();


    public void get() {
        lock.lock();//lock/unlock必须两两配对，不然会卡死
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        } finally {
            lock.unlock();
        }

    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked set()");
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void run() {
        try {
            get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}