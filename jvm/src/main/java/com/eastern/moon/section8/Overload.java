package com.eastern.moon.section8;

import java.io.Serializable;

/**
 *   自动类型转换      自动类型转换
 * char   ->     int    ->     long
 *     自动装箱             自动类型转换          自动类型转换
 * char   ->     Character  ->      Serializable -> Object
 *      自动类型转换
 * char   ->    char...
 * @author moon
 * @date 2023/10/17  22:30
 */
public class Overload {

    /*public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }*/

    /*public static void sayHello(int arg) {
        System.out.println("hello int");
    }*/

    /*public static void sayHello(long arg) {
        System.out.println("hello long");
    }*/

    /*public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }*/

    /*public static void sayHello(char arg) {
        System.out.println("hello char");
    }*/

    public static void sayHello(char... arg) {
        System.out.println("hello char...");
    }

    /*public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }*/

    public static void main(String[] args) {
        sayHello('a');
    }
}
