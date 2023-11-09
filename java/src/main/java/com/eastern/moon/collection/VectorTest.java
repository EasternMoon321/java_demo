package com.eastern.moon.collection;

import java.util.Vector;

/**
 * @author moon
 */
public class VectorTest {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < 10; ++i) {
            vector.add(i);
        }
        for (int i = 10; i < 20; ++i) {
            vector.add(i);
        }
        vector.add(21);
        vector.add(21);
        System.out.println(vector);
    }
}
