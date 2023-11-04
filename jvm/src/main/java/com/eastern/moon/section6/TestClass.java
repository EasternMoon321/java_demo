package com.eastern.moon.section6;

/**
 * @author moon
 * @date 2023/10/9  22:43
 */
public class TestClass {
    private int m;
    private int z =Short.MAX_VALUE + 1;
    public int inc() {
        return m + 1;
    }

    public int ine() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
