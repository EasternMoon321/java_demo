package com.eastern.moon.section7;

/**
 * 通过子类引用父类静态字段，不会导致子类初始化
 * @author moon
 * @date 2023/10/14  18:43
 */
public class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }
    public static int value = 123;
}
