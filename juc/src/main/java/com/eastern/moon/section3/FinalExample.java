package com.eastern.moon.section3;

/**
 * 1. 构造函数内final域堆写入，与被构造对象堆引用赋值给引用变量，不能重排序
 * 2. 初次读包含final域对象的引用，与随后初次读这个final域，不能重排序
 * @author moon
 * @date 2023/10/26  22:09
 */
public class FinalExample {
    int i;
    final int j;
    static FinalExample obj;
    public FinalExample() {
        i = 1;
        j = 2;
    }

    public static void writer() {
        obj = new FinalExample();
    }

    public static void reader() {
        FinalExample object = obj;
        int a = object.i;
        int b = object.j;
    }

}
