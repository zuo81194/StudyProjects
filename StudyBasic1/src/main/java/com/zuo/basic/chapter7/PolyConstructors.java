package com.zuo.basic.chapter7;


abstract class Glyph {
    abstract void draw();
    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}
class RoundGlyph extends Glyph {
    int radius = 1;
    RoundGlyph(int r) {
        radius = r;
        System.out.println(
                "RoundGlyph.RoundGlyph(), radius = "
                        + radius);
    }
    @Override
    void draw() {
        System.out.println(
                "RoundGlyph.draw(), radius = " + radius);
    }
}
public class PolyConstructors {
    /**
     * 为什么会出现这样的情况呢，是因为基类先创建的时候，所创建的类只进行了第一次初始化，所以打印出来为0
     */
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}
