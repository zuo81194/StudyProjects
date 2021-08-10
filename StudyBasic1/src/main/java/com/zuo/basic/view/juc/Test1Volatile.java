package com.zuo.basic.view.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * volatile三大特性
 * 1. 保证可见性
 * 2.不保证原子性
 * 3.禁止指令重排
 */

/**
 * done 2~4课程讲解volatile特性一:保证可见性
 * zlp studies on 2021/6/20
 */
public class Test1Volatile {

    /**
     * 1   验证volatile的可见性
     * 1.1  加入int number = 0; number 变量之前并没有添加volatile关键字修饰
     */
    public static void main(String[] args) {
        MyData1 myData = new MyData1();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + "\t update number value" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        //第二个线程，main线程
        while (myData.number == 0) {
            //main线程一直停在这里等待循环，直到number的值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }

}

class Test2Volatile {

    /**
     * 1   验证volatile的可见性
     * 1.1  加入int number = 0; number 变量之前并没有添加volatile关键字修饰，这次加上volatile修饰，会通知其他线程
     */
    public static void main(String[] args) {
        MyData2 myData = new MyData2();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + "\t update number value" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        //第二个线程，main线程
        while (myData.number == 0) {
            //main线程一直停在这里等待循环，知道number的值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }

}

/**
 * number 变量之前并没有添加volatile关键字修饰
 */
class MyData1 {
    //共享变量
    int number = 0;

    public void addTo60() {
        this.number = 60;
    }

}

/**
 * number 变量添加volatile关键字修饰
 */
class MyData2 {//MyData2.java --> MyData2.class --> JVM字节码
    //共享变量
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

}

/**
 * done 5~7课程验证volatile特性二：不保证原子性
 * 为什么number++在多线程的情况下会出现这种现象
 * 原理：由于number++底层的操作是由三步完成的（1.获得初始值，2.加1操作，3.把累加的值写回），
 * 多线程情况下可能会被加塞，值改变了也没来得及通知子线程，出现丢失写值的情况
 * 解决方案：1.加sync
 * 2.使用juc下的AtomicInteger（底层原理：CAS-->compare and swap）
 */
class Test3Volatile {
    public static void main(String[] args) {
        MyData2 myData2 = new MyData2();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    //不保证原子性
                    myData2.addPlusPlus();
                    //保证原子性的解决方案(使用AtomicInteger)
                    myData2.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t int type finally number value: " + myData2.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type finally number value: " + myData2.atomicInteger);
    }

    public static void seeOkVolatile() {

    }
}

/**
 * done 8~9 volatile指令重排案例1、2
 */
class Test4Volatile {
    public void test1() {
        int i = 5;
        int j = 4;
        i = i + 5;
        j = i * 4;
        //以上可能发生指令重排
    }
}

/**
 * 多线程多线程情况下，线程交替执行可能导致变量乱序执行
 */
class ReSortDemo {
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true;//
    }

    public void method02() {
        if (flag) {
            a = a + 5;
            System.out.println("********retValue: " + a);
        }
    }

    public static void main(String[] args) {
        ReSortDemo demo = new ReSortDemo();
        new Thread(demo::method02, "name2");
        new Thread(demo::method01, "name1");

    }
}

/**
 * done 第10课：单例模式在多线程情况下可能存在的安全问题
 */
class SingletonDemo {//单机版的单例模式
    private static SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程(mian现成的操作动作)
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println();
        System.out.println();
        System.out.println();
        //多线程给环境下模拟
        for (int i = 1; i <= 10; i++) {
            new Thread(SingletonDemo::getInstance, String.valueOf(i)).start();
        }
    }
}

/**
 * done 第11课：单例模式volatile优化
 * <p>
 * DCL的单例模式
 * DCL的单例模式的潜在隐患，不一定线程安全，原因是有指令重排的存在，加入volatile可以禁止指令重排
 * 对象初始化三步：
 * 1.分配内存空间；
 * 2.初始化对象；
 * 3.设置instance指向刚分配的内存地址，此时的instance!=null
 * 步骤2和步骤3不存在数据依赖关系，而且不论重排前还是重排后程序的执行结果在线程中并没有改变，因此这种重排是允许的，所以可能导致的顺序是1.3.2
 */
class SingletonDCLDemo {//单机版的单例模式
    //1.
//    private static SingletonDCLDemo instance = null;
    //2.禁止指令重排
    private static volatile SingletonDCLDemo instance = null;

    private SingletonDCLDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
    }

    //DCL (Double Check lock双端检锁机制)
    public static SingletonDCLDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDCLDemo.class) {
                if (instance == null) {
                    instance = new SingletonDCLDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(SingletonDCLDemo::getInstance, String.valueOf(i)).start();
        }
    }
}

/**
 * done 第12课：CAS是什么：比较并交换 Compare-And-Swap ,它是一条CPU并发原语，偏硬件，不会被打断
 * CAS ==> compareAndSet
 */
class CASDemo {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);
        //expectedValue期望值作用：比较副本跟主物理内存值是否一致，若一致，则可以修改为newValue新值
        System.out.println(atomicInteger.compareAndSet(5, 2021) + "\t current data: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data: " + atomicInteger.get());

        atomicInteger.getAndIncrement();
        //！注意：AtomicInteger对象的value值是被volatile修饰的，所以底层比较交换的时候，若value被修改，这里是可见的
        //底层
//        public final int getAndIncrement() {
//            return U.getAndAddInt(this, VALUE, 1);
//        }

        //U = jdk.internal.misc.Unsafe.getUnsafe();
        //jdk.internal.misc.Unsafe
        //Unsafe的方法都是native修饰，native

//        public final int getAndAddInt(Object o, long offset, int delta) {
//            int v;
//            do {
//                v = getIntVolatile(o, offset);
//            } while (!weakCompareAndSetInt(o, offset, v, v + delta));
        //weakCompareAndSetInt: 是native方法，是汇编语言，一定是原子的
//            return v;
//        }
    }
}

/**
 * done 第13~14课：CAS底层原理
 * 1.自旋锁=CAS+Unsafe 用do-while实现自旋
 * 2.Unsafe类
 * <p>
 * done 第15课：CAS缺点
 * 1. 循环时间长开销很大
 * 有可能在高并发情况下do-while一直在循环比较，开销大
 * 2. 只能保证一个共享变量的原子操作
 * 对于多个共享变量操作时，循环CAS就无法保证操作的原子性，这时候需要加锁来保证原子性
 * 3. ABA问题
 * <p>
 * done 第16课：ABA问题
 * ABA: 狸猫换太子、透偷梁换柱  副本与主物理内存比较相同时，有可能出现主物理内存被改过的情况，只是结果导致值没有变，这里主物理内存的值就非彼主物理的值，这就是ABA问题
 * <p>
 * done 第17课 如何解决ABA问题之理解原子引用AtomicReference+修改版本号（类似时间戳）
 */
class User {
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * 理解原子引用
 */
class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3", 22);
        User l4 = new User("l4", 24);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t " + atomicReference.get().toString());
    }
}

/**
 * todo 第18~19课 时间戳原子引用，ABA问题解决
 */
class ABADemo {
    static AtomicReference<Integer> num = new AtomicReference<>(100);//实验ABA问题成功
    //    static AtomicReference<Integer> num = new AtomicReference<>(200);//实验ABA问题失败 为什么？？？
    static AtomicStampedReference<Integer> integer = new AtomicStampedReference<Integer>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            num.compareAndSet(100, 101);
            num.compareAndSet(101, 100);
            try {
                //这里确保T1执行完，T2再执行
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();
        new Thread(() -> {
            boolean b = num.compareAndSet(100, 2021);
            System.out.println("T2执行结果：" + b + "\t 当前对象结果：" + num.get());
        }, "T2").start();
        //解决ABA问题分割线
        try {
            //这里确保T1T2执行完，再执行以下逻辑
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------------以下解决ABA问题----------------");
        new Thread(() -> {
            int stamp = integer.getStamp();
            System.out.println("T3修改前版本号：" + stamp);
            try {
                //这里确保T3和T4取到相同的版本号，模拟高并发环境的情况
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            integer.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println("T3第1次修改，值：" + integer.getReference() + "\t 版本号：" + integer.getStamp());
            integer.compareAndSet(101, 100, integer.getStamp(), integer.getStamp() + 1);
            System.out.println("T3第2次修改，值：" + integer.getReference() + "\t 版本号：" + integer.getStamp());

        }, "T3").start();
        new Thread(() -> {
            int stamp = integer.getStamp();
            System.out.println("T4修改前版本号：" + stamp);
            try {
                //这里确保T3完成ABA操作
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = integer.compareAndSet(100, 2021, stamp, stamp + 1);
            System.out.println("T4执行结果：" + b + "\t 当前对象结果：" + integer.getReference());
            System.out.println("T4执行结果：" + b + "\t 当前对象版本号：" + integer.getStamp());
        }, "T4").start();
    }
}