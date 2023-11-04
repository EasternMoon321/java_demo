package com.eastern.moon.section4;

/**
 * -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 * @author moon
 * @date 2023/10/14  21:24
 */
public class JHSDR_TestCase {

    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }

    private static class ObjectHolder {}

    public static void main(String[] args) {
        Test test = new Test();
        test.foo();
    }
}
