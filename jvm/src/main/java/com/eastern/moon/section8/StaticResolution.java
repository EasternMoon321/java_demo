package com.eastern.moon.section8;

/**
 * Classfile /Users/moon/develop/JVM/target/classes/com/eastern/moon/section8/StaticResolution.class
 *   Last modified Oct 17, 2023; size 670 bytes
 *   SHA-256 checksum 8f0c52685cf837f82effc75b9a04717bb6d1c845d0d47a80f0dfc5ccfae75820
 *   Compiled from "StaticResolution.java"
 * public class com.eastern.moon.section8.StaticResolution
 *   minor version: 0
 *   major version: 61
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #22                         // com/eastern/moon/section8/StaticResolution
 *   super_class: #2                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 3, attributes: 1
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
 *   #13 = String             #14            // hello world
 *   #14 = Utf8               hello world
 *   #15 = Methodref          #16.#17        // java/io/PrintStream.println:(Ljava/lang/String;)V
 *   #16 = Class              #18            // java/io/PrintStream
 *   #17 = NameAndType        #19:#20        // println:(Ljava/lang/String;)V
 *   #18 = Utf8               java/io/PrintStream
 *   #19 = Utf8               println
 *   #20 = Utf8               (Ljava/lang/String;)V
 *   #21 = Methodref          #22.#23        // com/eastern/moon/section8/StaticResolution.sayHello:()V
 *   #22 = Class              #24            // com/eastern/moon/section8/StaticResolution
 *   #23 = NameAndType        #25:#6         // sayHello:()V
 *   #24 = Utf8               com/eastern/moon/section8/StaticResolution
 *   #25 = Utf8               sayHello
 *   #26 = Utf8               Code
 *   #27 = Utf8               LineNumberTable
 *   #28 = Utf8               LocalVariableTable
 *   #29 = Utf8               this
 *   #30 = Utf8               Lcom/eastern/moon/section8/StaticResolution;
 *   #31 = Utf8               main
 *   #32 = Utf8               ([Ljava/lang/String;)V
 *   #33 = Utf8               args
 *   #34 = Utf8               [Ljava/lang/String;
 *   #35 = Utf8               SourceFile
 *   #36 = Utf8               StaticResolution.java
 * {
 *   public com.eastern.moon.section8.StaticResolution();
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
 *             0       5     0  this   Lcom/eastern/moon/section8/StaticResolution;
 *
 *   public static void sayHello();
 *     descriptor: ()V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=0, args_size=0
 *          0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *          3: ldc           #13                 // String hello world
 *          5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *          8: return
 *       LineNumberTable:
 *         line 9: 0
 *         line 10: 8
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=0, locals=1, args_size=1
 *          0: .  #21                 // Method sayHello:()V
 *          3: return
 *       LineNumberTable:
 *         line 13: 0
 *         line 14: 3
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       4     0  args   [Ljava/lang/String;
 * }
 * SourceFile: "StaticResolution.java"
 * @author moon
 * @date 2023/10/17  22:05
 */
public class StaticResolution {
    public static void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }
}
