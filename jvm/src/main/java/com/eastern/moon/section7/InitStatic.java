package com.eastern.moon.section7;

/**
 * 静态语句块中只能访问到定义在静态语句块之前的变量，定义在它之后的变量，在前面的静态语句块可以赋值，但是不能访问
 * @author moon
 * @date 2023/10/15  03:50
 */
public class InitStatic {
    static {
        i = 0;
//        System.out.println(i);
    }

    public InitStatic() {
        super();
    }

    static int i = 1;

    public static void main(String[] args) {
        System.out.println(InitStatic.i);
    }
}
