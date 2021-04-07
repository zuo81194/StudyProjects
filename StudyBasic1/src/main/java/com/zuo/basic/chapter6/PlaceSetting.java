package com.zuo.basic.chapter6;

import com.zuo.basic.chapter7.*;

class Plate {
    Plate(int i) {
        System.out.println("Plate constructor");
    }
}

class DinnerPlate extends Plate {
    DinnerPlate(int i) {
        super(i);
        System.out.println(
                "DinnerPlate constructor");
    }
}

class Utensil {
    Utensil(int i) {
        System.out.println("Utensil constructor");
    }
}

class Spoon extends Utensil {
    Spoon(int i) {
        super(i);
        System.out.println("Spoon constructor");
    }
}

class Fork extends Utensil {
    Fork(int i) {
        super(i);
        System.out.println("Fork constructor");
    }
}

class Knife extends Utensil {
    Knife(int i) {
        super(i);
        System.out.println("Knife constructor");
    }
}

class Custom {
    Custom(int i) {
        System.out.println("Custom constructor");
    }
}

public class PlaceSetting extends Custom {
    Spoon sp;
    Fork frk;
    Knife kn;
    DinnerPlate pl;
    PlaceSetting(int i) {
        super(i + 1);
        sp = new Spoon(i + 2);
        frk = new Fork(i + 3);
        kn = new Knife(i + 4);
        pl = new DinnerPlate(i + 5);
        System.out.println(
                "PlaceSetting constructor");
    }
    public static void main(String[] args) {
        PlaceSetting x = new PlaceSetting(9);
    }
}

/**
 *
 * Custom constructor
 * Utensil constructor
 * Spoon constructor
 * Utensil constructor
 * Fork constructor
 * Utensil constructor
 * Knife constructor
 * Plate constructor
 * DinnerPlate constructor
 * PlaceSetting constructor
 *
 */

//不同包下，只有Instrument是public时才可以实现，否则编译报错
class i implements Instrument {

    @Override
    public void play() {

    }

    @Override
    public String what() {
        return null;
    }

    @Override
    public void adjust() {

    }
}














