package com.zuo.basic.chapter7;

/**
 * Description 乐器
 * create by zlp on 20200515
 */
public interface Instrument {

    void play();

    String what();

    void adjust();

}

/**
 * Description 风
 * create by zlp on 20200515
 */
class Wind implements Instrument {

    @Override
    public void play() {
        System.out.println("Wind.play()");
    }

    @Override
    public String what() {
        return "Wind";
    }

    @Override
    public void adjust() {

    }
}

/**
 * Description 打击乐器
 * create by zlp on 20200515
 */
class Percussion implements Instrument {

    @Override
    public void play() {
        System.out.println("Percussion.play()");
    }

    @Override
    public String what() {
        return "Percussion";
    }

    @Override
    public void adjust() {

    }
}

/**
 * Description 弦
 * create by zlp on 20200515
 */
class Stringed implements Instrument {

    @Override
    public void play() {
        System.out.println("Stringed.play()");
    }

    @Override
    public String what() {
        return "Stringed";
    }

    @Override
    public void adjust() {

    }
}

/**
 * Description 木管乐器
 * create by zlp on 20200515
 */
class Woodwind extends Wind {
    @Override
    public void play() {
        System.out.println("WoodWind.play()");
    }

    @Override
    public String what() {
        return "WoodWind";
    }
}

/**
 * Description 通关乐器
 * create by zlp on 20200515
 */
class Brass extends Wind {
    @Override
    public void play() {
        System.out.println("Brass.play()");
    }

    @Override
    public void adjust() {
        System.out.println("Brass.adjust()");
    }
}

//弹奏
class Music {
    static void tune(Instrument i) {
        i.play();
    }

    static void tuneAll(Instrument[] instruments) {
        for (Instrument instrument : instruments) {
            tune(instrument);
        }
    }

    public static void main(String[] args) {
        Instrument[] orchestra = new Instrument[5];
        int i = 0;
        // Upcasting during addition to the array:
        orchestra[i++] = new Wind();
        orchestra[i++] = new Percussion();
        orchestra[i++] = new Stringed();
        orchestra[i++] = new Brass();
        orchestra[i++] = new Woodwind();
        tuneAll(orchestra);
    }
}