package com.zuo.basic.chapter6;

public class CADSystem extends Shape{

    private Circle c;
    private Triangle t;
    private Line[] lines = new Line[10];
    CADSystem(int i) {
        super(i + 1);
        for(int j = 0; j < 10; j++) {
            lines[j] = new Line(j, j*j);
        }
        c = new Circle(1);
        t = new Triangle(1);
        System.out.println("Combined constructor");
    }

    @Override
    void cleanup() {
        System.out.println("CADSystem.cleanup()");
        t.cleanup();
        c.cleanup();
        for(int i = 0; i < lines.length; i++) {
            lines[i].cleanup();
        }
        super.cleanup();
    }
    public static void main(String[] args) {
        CADSystem x = new CADSystem(47);
        try {
// Code and exception handling...
        } finally {
            x.cleanup();
        }
    }

}

class Shape {
    Shape(int i) {
        System.out.println("Shape constructor");
    }
    void cleanup() {
        System.out.println("Shape cleanup");
    }
}

class Circle extends Shape{
    public Circle(int i) {
        super(i);
    }

    @Override
    void cleanup() {
        System.out.println("Erasing a Circle");
        super.cleanup();
    }
}

class Triangle extends Shape {
    Triangle(int i) {
        super(i);
        System.out.println("Drawing a Triangle");
    }
    @Override
    void cleanup() {
        System.out.println("Erasing a Triangle");
        super.cleanup();
    }
}

class Line extends Shape {
    private int start, end;
    Line(int start, int end) {
        super(start);
        this.start = start;
        this.end = end;
        System.out.println("Drawing a Line: " +
                start + ", " + end);
    }
    @Override
    void cleanup() {
        System.out.println("Erasing a Line: " +
                start + ", " + end);
        super.cleanup();
    }
}

class Homer {
    char doh(char c) {
        System.out.println("doh(char)");
        return 'd';
    }

    float doh(float f) {
        System.out.println("doh(float)");
        return 1.0f;
    }
}
class Milhouse {}
class Bart extends Homer {
    void doh(Milhouse m) {}
}
class Hide {
    public static void main(String[] args) {
        Bart b = new Bart();
        b.doh(1); // doh(float) used
        b.doh('x');
        b.doh(1.0f);
        b.doh(new Milhouse());
    }
}






