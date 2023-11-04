package com.eastern.moon.section8;

/**
 * 重载时 是 通 过 参 数 的 静 态 类 型 而 不 是 实 际 类 型 作 为 判 定 依 据
 * Classfile /Users/moon/develop/JVM/target/classes/com/eastern/moon/section8/StaticDispatch.class
 *   Last modified Oct 17, 2023; size 1567 bytes
 *   SHA-256 checksum d977f9fedd7b9032e27e94cc5d5dd0e0819eb065930d21b1000f5cc677879421
 *   Compiled from "StaticDispatch.java"
 * public class com.eastern.moon.section8.StaticDispatch
 *   minor version: 0
 *   major version: 61
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #31                         // com/eastern/moon/section8/StaticDispatch
 *   super_class: #2                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 5, attributes: 3
 * Constant pool:
 *    #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
 *    #2 = Class              #4             // java/lang/Object
 *    #3 = NameAndType        #5:#6          // "<init>":()V
 *    #4 = Utf8               java/lang/Object
 *    #5 = Utf8               <init>
 *    #6 = Utf8               ()V
 *    #7 = Fieldref           #8.#9          // java/lang/System.out:Ljava/io/PrintStream;
 *    #8 = Class              #10            // java/lang/System
 *    #9 = NameAndType        #11:#12        // out:Ljava/io/PrintStream;
 *   #10 = Utf8               java/lang/System
 *   #11 = Utf8               out
 *   #12 = Utf8               Ljava/io/PrintStream;
 *   #13 = String             #14            // hello,guy!
 *   #14 = Utf8               hello,guy!
 *   #15 = Methodref          #16.#17        // java/io/PrintStream.println:(Ljava/lang/String;)V
 *   #16 = Class              #18            // java/io/PrintStream
 *   #17 = NameAndType        #19:#20        // println:(Ljava/lang/String;)V
 *   #18 = Utf8               java/io/PrintStream
 *   #19 = Utf8               println
 *   #20 = Utf8               (Ljava/lang/String;)V
 *   #21 = String             #22            // hello,gentleman!
 *   #22 = Utf8               hello,gentleman!
 *   #23 = String             #24            // hello,lady!
 *   #24 = Utf8               hello,lady!
 *   #25 = Class              #26            // com/eastern/moon/section8/StaticDispatch$Man
 *   #26 = Utf8               com/eastern/moon/section8/StaticDispatch$Man
 *   #27 = Methodref          #25.#3         // com/eastern/moon/section8/StaticDispatch$Man."<init>":()V
 *   #28 = Class              #29            // com/eastern/moon/section8/StaticDispatch$Woman
 *   #29 = Utf8               com/eastern/moon/section8/StaticDispatch$Woman
 *   #30 = Methodref          #28.#3         // com/eastern/moon/section8/StaticDispatch$Woman."<init>":()V
 *   #31 = Class              #32            // com/eastern/moon/section8/StaticDispatch
 *   #32 = Utf8               com/eastern/moon/section8/StaticDispatch
 *   #33 = Methodref          #31.#3         // com/eastern/moon/section8/StaticDispatch."<init>":()V
 *   #34 = Methodref          #31.#35        // com/eastern/moon/section8/StaticDispatch.sayHello:(Lcom/eastern/moon/section8/StaticDispatch$Human;)V
 *   #35 = NameAndType        #36:#37        // sayHello:(Lcom/eastern/moon/section8/StaticDispatch$Human;)V
 *   #36 = Utf8               sayHello
 *   #37 = Utf8               (Lcom/eastern/moon/section8/StaticDispatch$Human;)V
 *   #38 = Utf8               Code
 *   #39 = Utf8               LineNumberTable
 *   #40 = Utf8               LocalVariableTable
 *   #41 = Utf8               this
 *   #42 = Utf8               Lcom/eastern/moon/section8/StaticDispatch;
 *   #43 = Utf8               guy
 *   #44 = Utf8               Lcom/eastern/moon/section8/StaticDispatch$Human;
 *   #45 = Utf8               (Lcom/eastern/moon/section8/StaticDispatch$Man;)V
 *   #46 = Utf8               Lcom/eastern/moon/section8/StaticDispatch$Man;
 *   #47 = Utf8               (Lcom/eastern/moon/section8/StaticDispatch$Woman;)V
 *   #48 = Utf8               Lcom/eastern/moon/section8/StaticDispatch$Woman;
 *   #49 = Utf8               main
 *   #50 = Utf8               ([Ljava/lang/String;)V
 *   #51 = Utf8               args
 *   #52 = Utf8               [Ljava/lang/String;
 *   #53 = Utf8               man
 *   #54 = Utf8               woman
 *   #55 = Utf8               sr
 *   #56 = Utf8               SourceFile
 *   #57 = Utf8               StaticDispatch.java
 *   #58 = Utf8               NestMembers
 *   #59 = Class              #60            // com/eastern/moon/section8/StaticDispatch$Human
 *   #60 = Utf8               com/eastern/moon/section8/StaticDispatch$Human
 *   #61 = Utf8               InnerClasses
 *   #62 = Utf8               Man
 *   #63 = Utf8               Woman
 *   #64 = Utf8               Human
 * {
 *   public com.eastern.moon.section8.StaticDispatch();
 *     descriptor: ()V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 7: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/eastern/moon/section8/StaticDispatch;
 *
 *   public void sayHello(com.eastern.moon.section8.StaticDispatch$Human);
 *     descriptor: (Lcom/eastern/moon/section8/StaticDispatch$Human;)V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=2, locals=2, args_size=2
 *          0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *          3: ldc           #13                 // String hello,guy!
 *          5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *          8: return
 *       LineNumberTable:
 *         line 16: 0
 *         line 17: 8
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       9     0  this   Lcom/eastern/moon/section8/StaticDispatch;
 *             0       9     1   guy   Lcom/eastern/moon/section8/StaticDispatch$Human;
 *
 *   public void sayHello(com.eastern.moon.section8.StaticDispatch$Man);
 *     descriptor: (Lcom/eastern/moon/section8/StaticDispatch$Man;)V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=2, locals=2, args_size=2
 *          0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *          3: ldc           #21                 // String hello,gentleman!
 *          5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *          8: return
 *       LineNumberTable:
 *         line 19: 0
 *         line 20: 8
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       9     0  this   Lcom/eastern/moon/section8/StaticDispatch;
 *             0       9     1   guy   Lcom/eastern/moon/section8/StaticDispatch$Man;
 *
 *   public void sayHello(com.eastern.moon.section8.StaticDispatch$Woman);
 *     descriptor: (Lcom/eastern/moon/section8/StaticDispatch$Woman;)V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=2, locals=2, args_size=2
 *          0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *          3: ldc           #23                 // String hello,lady!
 *          5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *          8: return
 *       LineNumberTable:
 *         line 22: 0
 *         line 23: 8
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       9     0  this   Lcom/eastern/moon/section8/StaticDispatch;
 *             0       9     1   guy   Lcom/eastern/moon/section8/StaticDispatch$Woman;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=4, args_size=1
 *          0: new           #25                 // class com/eastern/moon/section8/StaticDispatch$Man
 *          3: dup
 *          4: invokespecial #27                 // Method com/eastern/moon/section8/StaticDispatch$Man."<init>":()V
 *          7: astore_1
 *          8: new           #28                 // class com/eastern/moon/section8/StaticDispatch$Woman
 *         11: dup
 *         12: invokespecial #30                 // Method com/eastern/moon/section8/StaticDispatch$Woman."<init>":()V
 *         15: astore_2
 *         16: new           #31                 // class com/eastern/moon/section8/StaticDispatch
 *         19: dup
 *         20: invokespecial #33                 // Method "<init>":()V
 *         23: astore_3
 *         24: aload_3
 *         25: aload_1
 *         26: invokevirtual #34                 // Method sayHello:(Lcom/eastern/moon/section8/StaticDispatch$Human;)V
 *         29: aload_3
 *         30: aload_2
 *         31: invokevirtual #34                 // Method sayHello:(Lcom/eastern/moon/section8/StaticDispatch$Human;)V
 *         34: return
 *       LineNumberTable:
 *         line 27: 0
 *         line 28: 8
 *         line 29: 16
 *         line 30: 24
 *         line 31: 29
 *         line 32: 34
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      35     0  args   [Ljava/lang/String;
 *             8      27     1   man   Lcom/eastern/moon/section8/StaticDispatch$Human;
 *            16      19     2 woman   Lcom/eastern/moon/section8/StaticDispatch$Human;
 *            24      11     3    sr   Lcom/eastern/moon/section8/StaticDispatch;
 * }
 * SourceFile: "StaticDispatch.java"
 * NestMembers:
 *   com/eastern/moon/section8/StaticDispatch$Woman
 *   com/eastern/moon/section8/StaticDispatch$Man
 *   com/eastern/moon/section8/StaticDispatch$Human
 * InnerClasses:
 *   static #62= #25 of #31;                 // Man=class com/eastern/moon/section8/StaticDispatch$Man of class com/eastern/moon/section8/StaticDispatch
 *   static #63= #28 of #31;                 // Woman=class com/eastern/moon/section8/StaticDispatch$Woman of class com/eastern/moon/section8/StaticDispatch
 *   static abstract #64= #59 of #31;        // Human=class com/eastern/moon/section8/StaticDispatch$Human of class com/eastern/moon/section8/StaticDispatch
 * @author moon
 * @date 2023/10/17  22:18
 */
public class StaticDispatch {

    static abstract class Human {}

    static class Man extends Human {}

    static class Woman extends Human {}

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }
    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }
    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        // 静态类型  实际类型
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}
