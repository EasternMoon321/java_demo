package com.eastern.moon.section3;

/**
 * @author moon
 * @date 2023/10/26  22:14
 */
public class FinalReferenceExample {
    final int[] intArray;
    static FinalReferenceExample obj;
    public FinalReferenceExample() {
        intArray = new int[1];
        intArray[0] = 1;
    }

    public static void writerOne() {
        obj = new FinalReferenceExample();
    }

    public static void WriterTwo() {
        obj.intArray[0] = 2;
    }

    public static void reader() {
        if (obj != null) {
            int temp1 = obj.intArray[0];
        }
    }
}
