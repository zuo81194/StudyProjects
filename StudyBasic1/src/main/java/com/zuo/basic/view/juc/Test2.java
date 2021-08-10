package com.zuo.basic.view.juc;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
 * done 26~27:java锁之可重入锁和递归锁理论知识
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

/**
 * done 28: java锁之自旋锁理论知识
 * 自旋锁（spinlock）:是指尝试获取锁的线程不会立即阻塞，
 * 而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU
 * CAS自旋锁:while(compareAndSwapInt(var1,var2,var5,var+var4))就是循环尝试
 * done 29:自旋锁代码验证
 * 好处：循环获取没有阻塞
 */
class SpinLockDemo {

    AtomicReference<Thread> reference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t come in 😄");
        while (!reference.compareAndSet(null, thread)) {
        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "\t go out (_　_)。゜zｚＺ");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unLock();
        }, "AA").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.unLock();
        }, "BB").start();
    }
}

/**
 * done 第30课：读写锁理论知识
 * 独占锁：指该锁被一个线程持有，对ReetranLock和Synchronized而言都是独占锁
 * 共享锁：可被多个线程持有
 * 对ReentrantReadWriteLock其读锁是共享锁，其写锁是独占锁
 * done 31课：读写锁小demo验证
 * 为什么要用读写锁：
 */
class ReadWriteDemo {
    public static void main(String[] args) {
        //不加锁小栗子
//        MyCache1 myCache1 = new MyCache1();
//        for (int i = 1; i <= 5; i++) {
//            String tmp = String.valueOf(i);
//            new Thread(() -> {
//                myCache1.put(tmp, tmp);
//            }, tmp).start();
//        }
//        for (int i = 1; i <= 5; i++) {
//            String tmp = String.valueOf(i);
//            new Thread(() -> {
//                myCache1.get(tmp);
//            }, tmp).start();
//        }
        //加锁小例子
        MyCache2 myCache2 = new MyCache2();
        for (int i = 1; i <= 5; i++) {
            String tmp = String.valueOf(i);
            new Thread(() -> {
                myCache2.put(tmp, tmp);
            }, tmp).start();
        }
        for (int i = 1; i <= 5; i++) {
            String tmp = String.valueOf(i);
            new Thread(() -> {
                myCache2.get(tmp);
            }, tmp).start();
        }
    }
}

class MyCache1 {//不加锁
    private Map map = new HashMap<String, String>();

    public void put(String key, String value) {
        System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + value);
        //模拟网络延迟
        try {
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t 正在读取");
        //模拟网络延迟
        try {
            TimeUnit.MICROSECONDS.sleep(300);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MyCache2 {//使用读写锁
    private Map map = new HashMap<String, String>();
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        rwLock.writeLock().lock();
        //模拟网络延迟
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + value);
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwLock.readLock().lock();
        //模拟网络延迟
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            TimeUnit.MICROSECONDS.sleep(300);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

/**
 * done 第32课：CountDownLatch讲解(灭六国一统华夏)
 * 案例一
 */
class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            int n = i;
            new Thread(() -> {
                System.out.println("第" + n + "位同学离开");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长锁门O(∩_∩)O");
    }
}

/**
 * 案例二
 */
class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            int n = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国被灭");
                countDownLatch.countDown();
            }, CountryEnum.foreachCounty(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println("秦国一统华夏");
        System.out.println();
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
    }
}

enum CountryEnum {
    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");

    private Integer retCode;
    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    //可以消化成我的code
    public static CountryEnum foreachCounty(Integer index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum country : values) {
            if (index.equals(country.retCode)) {
                return country;
            }
        }
        return null;
    }
}

/**
 * done 第33课：循环阻塞 CyclicBarrierDemo(集齐龙珠召唤神龙)
 * 案例一
 */
class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("**************召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println("第" + Thread.currentThread().getName() + "颗龙珠被搜集");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}


/**
 * done 34课：信号灯 SemaphoreDemo （争车位）
 * 用于多个线程抢占多个资源，用于并发线程数的控制
 */
class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "停3秒后离开车位");
                }
            }, String.valueOf(i)).start();
        }
    }
}


/**
 * todo 35~36课：阻塞队列理论
 * <p>
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列，此队列按FIFO(先进先出)原则对元素进行排序
 * LinkedBlockingQueue: 一个是基于链表结构的阻塞队列，此队列按FIFO(先进先出)排序元素,吞吐量通常要高于ArrayBlockingQueue
 * SynchronousQueue: 一个不存储元素的阻塞队列，也就是单个元素的队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高
 * <p>
 * 1 队列
 * 先到先得
 * 2 阻塞队列
 * 2.1 阻塞队列有没有好的一面
 * <p>
 * 2.2 不得不阻塞，你如何管理
 */
class BlockingQueueDemo {
    public static void main(String[] args) {
        List<String> li = new ArrayList<>();
    }
}