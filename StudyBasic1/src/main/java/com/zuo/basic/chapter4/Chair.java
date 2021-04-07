package com.zuo.basic.chapter4;

public class Chair {

    static boolean gcrun = false;
    static boolean f = false;
    static int created = 0;
    static int finalized = 0;
    int i;

    public Chair() {
        i = ++created;
        if(created == 47) {
            System.out.println("Created 47");
        }
    }

    void testNoneProtected1(){
        System.out.println("testNoneProtected1()");
    }

    protected void testHasProtected2(){
        System.out.println("testHasProtected2()");
    }

    @Override
    protected void finalize() throws Throwable {
        if(!gcrun) {
            gcrun = true;
            System.out.println(
                    "Beginning to finalize after " +
                            created + " Chairs have been created");
        }
        if(i == 47) {
            System.out.println(
                    "Finalizing Chair #47, " +
                            "Setting flag to stop Chair creation");
            f = true;
        }
        finalized++;
        if(finalized >= created) {
            System.out.println(
                    "All " + finalized + " finalized");
        }
    }
}
