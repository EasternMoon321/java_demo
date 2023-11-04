package com.eastern.moon.section2;

import java.util.HashSet;

/**
 * jdk 6  永久代   -XX:PerSize=6M -XX:MaxPerSize=10M
 * jdk 7+   -Xmx6m  -XX:MaxMetaspaceSize=6M
 * 字符串常量池，jdk7+以后，堆空间
 * intern(): 字符串没有出现过，在常量池中创建，并返回常量池地址；字符串出现过，返回出现过对象或字面量地址。
 *              字符串常量池中对应字面量地址
 * String s1 = new String("a"): 指向堆空间String对象的引用。
 * s1.intern():String对象中保存着一个final byte[]用于存储字符串的value，该成员又指向了字符串常量池中“字符串”这个字面量。因此调用s1.intern()，返回的是字符串常量池中"字符串"字面量的地址。s1本身存的是堆空间String对象的地址，因此二者不相等
 *
 * @author moon
 * @date 2023/10/1  16:00
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
/*
        String s1 = new String("a");
        // 字符串"a"出现在常量池，s1为指向堆空间String对象地址，s2为字面量"a"在常量池中地址
        System.out.println(s1 == s1.intern());
        String s2 = "a";    // 字符串常量池中"字符串"字面量的地址
        System.out.println(s1.intern() == s2);
        *//**
         * StringBuilder中维护着一个可变的byte[]用于存储数据，其append(String s)方法是为byte[]数组添加上一些数据，并更新byte[]的有效长度。
         * 因此在append过程中，是不会有任何新的字符串被产生的。
         * 仅当StringBuilder对象调用toString方法时，才会返回一个new出来的字符串。
         *//*
        String str1 = new StringBuilder("计算机").append("软件").toString();
        // 字符串"计算机"和"软件"分别出现在常量池，而"计算机软件"没有出现在常量池，str1 表示指向堆空间String对象地址，由于出现过，str1.intern() 指向堆空间String对象地址
        System.out.println(str1.intern() == str1);

        // java在加载sun.misc.Version类时，已被加载进常量池
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

        String str3 = new StringBuilder("la").append("ng").toString();
        System.out.println(str3.intern() == str3);*/

    }
}
