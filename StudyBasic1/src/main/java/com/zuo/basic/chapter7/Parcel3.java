package com.zuo.basic.chapter7;

abstract class Contents {
    abstract public int value();
}

interface Destination {
    String readLabel();
}

public class Parcel3 {

    private class PContents extends Contents {
        private int i = 11;

        @Override
        public int value() {
            // 类内的其它地方，即便是pravite的类也可以创建
            PDestination pDestination = new PDestination("");
            return i;
        }
    }

    protected class PDestination
            implements Destination {
        private String label;

        private PDestination(String whereTo) {
            label = whereTo;
            // 类内的其它地方，pravite的类也可以创建
            PContents pContents = new PContents();
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    public Destination dest(String s) {
        return new PDestination(s);
    }

    public Contents cont() {
        return new PContents();
    }
}

class Test extends Parcel3{
    public static void main(String[] args) {
        Parcel3 p = new Parcel3();
        Destination pDestination = p.dest("PDestination");
        System.out.println(pDestination.readLabel());
        Contents cont = p.cont();
        int value = cont.value();
        // Illegal -- can't access private class:
        // ! Parcel3.PContents pContents = p.new PContents();

    }

}
