package com.eastern.moon.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author moon
 */
public class ArrayListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(i);
        }
        for (int i = 10; i < 15; ++i) {
            list.add(i);
        }
        list.add(15);
        list.add(16);
        System.out.println(list);
    }
}
