package com.eastern.moon.section8;

/**
 * Compiled from "DynamicDispatch.java"
 * public class com.eastern.moon.section8.DynamicDispatch {
 *   public com.eastern.moon.section8.DynamicDispatch();
 *   public static void main(java.lang.String[]);
 * }
 * moon@EasternMoondeMacBook-Pro section8 % javap -verbose DynamicDispatch
 * Warning: File ./DynamicDispatch.class does not contain class DynamicDispatch
 * Classfile /Users/moon/develop/JVM/target/classes/com/eastern/moon/section8/DynamicDispatch.class
 *   Last modified Oct 17, 2023; size 871 bytes
 *   SHA-256 checksum 183c244307082024b367b847e5a8a3e980a725a97ec1677bb96dfe98a68fe3b3
 *   Compiled from "DynamicDispatch.java"
 * public class com.eastern.moon.section8.DynamicDispatch
 *   minor version: 0
 *   major version: 61
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #18                         // com/eastern/moon/section8/DynamicDispatch
 *   super_class: #2                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 2, attributes: 3
 * Constant pool:
 *    #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
 *    #2 = Class              #4             // java/lang/Object
 *    #3 = NameAndType        #5:#6          // "<init>":()V
 *    #4 = Utf8               java/lang/Object
 *    #5 = Utf8               <init>
 *    #6 = Utf8               ()V
 *    #7 = Class              #8             // com/eastern/moon/section8/DynamicDispatch$Man
 *    #8 = Utf8               com/eastern/moon/section8/DynamicDispatch$Man
 *    #9 = Methodref          #7.#3          // com/eastern/moon/section8/DynamicDispatch$Man."<init>":()V
 *   #10 = Class              #11            // com/eastern/moon/section8/DynamicDispatch$Woman
 *   #11 = Utf8               com/eastern/moon/section8/DynamicDispatch$Woman
 *   #12 = Methodref          #10.#3         // com/eastern/moon/section8/DynamicDispatch$Woman."<init>":()V
 *   #13 = Methodref          #14.#15        // com/eastern/moon/section8/DynamicDispatch$Human.sayHello:()V
 *   #14 = Class              #16            // com/eastern/moon/section8/DynamicDispatch$Human
 *   #15 = NameAndType        #17:#6         // sayHello:()V
 *   #16 = Utf8               com/eastern/moon/section8/DynamicDispatch$Human
 *   #17 = Utf8               sayHello
 *   #18 = Class              #19            // com/eastern/moon/section8/DynamicDispatch
 *   #19 = Utf8               com/eastern/moon/section8/DynamicDispatch
 *   #20 = Utf8               Code
 *   #21 = Utf8               LineNumberTable
 *   #22 = Utf8               LocalVariableTable
 *   #23 = Utf8               this
 *   #24 = Utf8               Lcom/eastern/moon/section8/DynamicDispatch;
 *   #25 = Utf8               main
 *   #26 = Utf8               ([Ljava/lang/String;)V
 *   #27 = Utf8               args
 *   #28 = Utf8               [Ljava/lang/String;
 *   #29 = Utf8               man
 *   #30 = Utf8               Lcom/eastern/moon/section8/DynamicDispatch$Human;
 *   #31 = Utf8               woman
 *   #32 = Utf8               SourceFile
 *   #33 = Utf8               DynamicDispatch.java
 *   #34 = Utf8               NestMembers
 *   #35 = Utf8               InnerClasses
 *   #36 = Utf8               Man
 *   #37 = Utf8               Woman
 *   #38 = Utf8               Human
 * {
 *   public com.eastern.moon.section8.DynamicDispatch();
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
 *             0       5     0  this   Lcom/eastern/moon/section8/DynamicDispatch;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=3, args_size=1
 *          0: new           #7                  // class com/eastern/moon/section8/DynamicDispatch$Man
 *          3: dup
 *          4: invokespecial #9                  // Method com/eastern/moon/section8/DynamicDispatch$Man."<init>":()V
 *          7: astore_1
 *          8: new           #10                 // class com/eastern/moon/section8/DynamicDispatch$Woman
 *         11: dup
 *         12: invokespecial #12                 // Method com/eastern/moon/section8/DynamicDispatch$Woman."<init>":()V
 *         15: astore_2
 *         16: aload_1
 *         17: invokevirtual #13                 // Method com/eastern/moon/section8/DynamicDispatch$Human.sayHello:()V
 *         20: aload_2
 *         21: invokevirtual #13                 // Method com/eastern/moon/section8/DynamicDispatch$Human.sayHello:()V
 *         24: new           #10                 // class com/eastern/moon/section8/DynamicDispatch$Woman
 *         27: dup
 *         28: invokespecial #12                 // Method com/eastern/moon/section8/DynamicDispatch$Woman."<init>":()V
 *         31: astore_1
 *         32: aload_1
 *         33: invokevirtual #13                 // Method com/eastern/moon/section8/DynamicDispatch$Human.sayHello:()V
 *         36: return
 *       LineNumberTable:
 *         line 31: 0
 *         line 32: 8
 *         line 33: 16
 *         line 34: 20
 *         line 35: 24
 *         line 36: 32
 *         line 37: 36
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      37     0  args   [Ljava/lang/String;
 *             8      29     1   man   Lcom/eastern/moon/section8/DynamicDispatch$Human;
 *            16      21     2 woman   Lcom/eastern/moon/section8/DynamicDispatch$Human;
 * }
 * SourceFile: "DynamicDispatch.java"
 * NestMembers:
 *   com/eastern/moon/section8/DynamicDispatch$Woman
 *   com/eastern/moon/section8/DynamicDispatch$Man
 *   com/eastern/moon/section8/DynamicDispatch$Human
 * InnerClasses:
 *   static #36= #7 of #18;                  // Man=class com/eastern/moon/section8/DynamicDispatch$Man of class com/eastern/moon/section8/DynamicDispatch
 *   static #37= #10 of #18;                 // Woman=class com/eastern/moon/section8/DynamicDispatch$Woman of class com/eastern/moon/section8/DynamicDispatch
 *   static abstract #38= #14 of #18;        // Human=class com/eastern/moon/section8/DynamicDispatch$Human of class com/eastern/moon/section8/DynamicDispatch
 * @author moon
 * @date 2023/10/17  22:18
 */
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("hello,gentleman!");
        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("hello,lady!");
        }
    }

    public static void main(String[] args) {
        // 静态类型  实际类型
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
