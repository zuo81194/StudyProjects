package com.zuo.xiaodi.interview.chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class Test2List {

    public static void main(String[] args) {
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<Object> lists = new CopyOnWriteArrayList<>();
    }

}
