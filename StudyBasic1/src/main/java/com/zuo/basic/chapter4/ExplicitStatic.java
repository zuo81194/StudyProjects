package com.zuo.basic.chapter4;

public class ExplicitStatic {

    static void f(Object[] o){
        for (int i = 0 ;i<o.length;i++) {
            System.out.println(o[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("Inside main()");
        Cups.c1.f(99); // (1)

        int[] a = {1, 2};
        Integer[] a1 = {
                new Integer(1),
                new Integer(2),
                new Integer(3)
        };

        Integer[] a2 = new Integer[]{
                new Integer(1),
                new Integer(2),
                new Integer(3)
        };

        f(new Object[]{
                new Integer(2),
                new ExplicitStatic(),
                new Float(3.14),
                new Double(11.11)
        });
        f(new Object[] {"one", "two", "three" });
        f(new Object[] {new A(), new A(), new A()});

    }

    static Cups x = new Cups(); // (2)
    static Cups y = new Cups(); // (2)
}

class A { int i;
public A(){}
}

class Cup {

    Cup(int marker) {
        System.out.println("Cup(" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }

}

class Cups {
    static Cup c1;
    static Cup c2;

    static {
        c1 = new Cup(1);
        c2 = new Cup(2);
    }

    Cups() {
        System.out.println("Cups()");
    }
}


