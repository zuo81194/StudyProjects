package com.zuo.basic.chapter7;

class DoBaseFinalization {
    public static boolean flag = false;
}

/**
 * 特性
 */
class Characteristic {
    String s;

    Characteristic(String c) {
        s = c;
        System.out.println("Creating Characteristic " + s);
    }

    @Override
    protected void finalize() {
        System.out.println(
                "finalizing Characteristic " + s);
    }
}

/**
 * 生物
 */
class LivingCreature {
    Characteristic p = new Characteristic("is alive");

    LivingCreature() {
        System.out.println("LivingCreature()");
    }

    @Override
    protected void finalize() {
        System.out.println("LivingCreature finalize");
        if (DoBaseFinalization.flag) {
            try {
                super.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}

/**
 * 动物
 */
class Animal extends LivingCreature {
    Characteristic p = new Characteristic("has heart");

    Animal() {
        System.out.println("Animal()");
    }

    @Override
    protected void finalize() {
        System.out.println("Animal finalize");
        if (DoBaseFinalization.flag) {
            try {
                super.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}

/**
 * 两栖动物
 */
class Amphibian extends Animal {
    Characteristic p =
            new Characteristic("can live in water");

    Amphibian() {
        System.out.println("Amphibian()");
    }

    @Override
    protected void finalize() {
        System.out.println("Amphibian finalize");
        if (DoBaseFinalization.flag) {
            try {
                super.finalize();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}

public class Frog extends Amphibian {
    Frog() {
        System.out.println("Frog()");
    }

    @Override
    protected void finalize() {
        System.out.println("Frog finalize");
        if (DoBaseFinalization.flag) {
            try {
                super.finalize();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 0 && args[0].equals("finalize")) {
            DoBaseFinalization.flag = true;
        } else {
            System.out.println("not finalizing bases");
            new Frog(); // Instantly becomes garbage
            System.out.println("bye!");
            // Must do this to guarantee that all
            // finalizers will be called:
            //System.runFinalizersOnExit(true);
        }
    }

}
