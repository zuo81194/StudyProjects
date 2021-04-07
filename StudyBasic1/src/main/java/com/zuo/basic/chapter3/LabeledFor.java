package com.zuo.basic.chapter3;

public class LabeledFor {

    public static void main(String[] args) {
        int i = 0;
        outerzlp:
        for (; true; ) {
            innerzlp:
            for (; i < 10; i++) {
                prt("i=" + i);
                if (i == 2) {
                    prt("continue");
                    continue;
                }
                if (i == 3) {
                    prt("break");
                    i++;
                    break;
                }
                if (i == 7) {
                    prt("continue outer");
                    i++;
                    continue outerzlp;
                }
                if (i == 8) {
                    prt("break outer");
                    break outerzlp;
                }
                for (int k = 0; k < 5; k++) {
                    if (k == 3) {
                        prt("continue inner");
                        continue innerzlp;
                    }
                }
            }
        }
        char a = 'a';
        int t = 0;
        byte b = 1;
        short s = 1;
        String g = "";
        switch (g){

        }
        //产生小于1的小数
        double random = Math.random();
    }

    static void prt(String s) {
        System.out.println(s);
    }

}
