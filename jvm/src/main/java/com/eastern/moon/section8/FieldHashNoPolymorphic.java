package com.eastern.moon.section8;

/**
 * 字段不参与多态 0, 4, 2
 *
 * Classfile /Users/moon/develop/JVM/target/classes/com/eastern/moon/section8/FieldHashNoPolymorphic.class
 *   Last modified Oct 17, 2023; size 1346 bytes
 *   SHA-256 checksum 22a44dc4081434769c6b4615f40b77786e56e921bbac6c7e6fc2bf17f8266a42
 *   Compiled from "FieldHashNoPolymorphic.java"
 * public class com.eastern.moon.section8.FieldHashNoPolymorphic
 *   minor version: 0
 *   major version: 61
 *   flags: (0x0021) ACC_PUBLIC, ACC_SUPER
 *   this_class: #32                         // com/eastern/moon/section8/FieldHashNoPolymorphic
 *   super_class: #2                         // java/lang/Object
 *   interfaces: 0, fields: 0, methods: 2, attributes: 4
 * Constant pool:
 *    #1 = Methodref          #2.#3          // java/lang/Object."<init>":()V
 *    #2 = Class              #4             // java/lang/Object
 *    #3 = NameAndType        #5:#6          // "<init>":()V
 *    #4 = Utf8               java/lang/Object
 *    #5 = Utf8               <init>
 *    #6 = Utf8               ()V
 *    #7 = Class              #8             // com/eastern/moon/section8/FieldHashNoPolymorphic$Son
 *    #8 = Utf8               com/eastern/moon/section8/FieldHashNoPolymorphic$Son
 *    #9 = Methodref          #7.#3          // com/eastern/moon/section8/FieldHashNoPolymorphic$Son."<init>":()V
 *   #10 = Fieldref           #11.#12        // java/lang/System.out:Ljava/io/PrintStream;
 *   #11 = Class              #13            // java/lang/System
 *   #12 = NameAndType        #14:#15        // out:Ljava/io/PrintStream;
 *   #13 = Utf8               java/lang/System
 *   #14 = Utf8               out
 *   #15 = Utf8               Ljava/io/PrintStream;
 *   #16 = Fieldref           #17.#18        // com/eastern/moon/section8/FieldHashNoPolymorphic$Father.money:I
 *   #17 = Class              #19            // com/eastern/moon/section8/FieldHashNoPolymorphic$Father
 *   #18 = NameAndType        #20:#21        // money:I
 *   #19 = Utf8               com/eastern/moon/section8/FieldHashNoPolymorphic$Father
 *   #20 = Utf8               money
 *   #21 = Utf8               I
 *   #22 = InvokeDynamic      #0:#23         // #0:makeConcatWithConstants:(I)Ljava/lang/String;
 *   #23 = NameAndType        #24:#25        // makeConcatWithConstants:(I)Ljava/lang/String;
 *   #24 = Utf8               makeConcatWithConstants
 *   #25 = Utf8               (I)Ljava/lang/String;
 *   #26 = Methodref          #27.#28        // java/io/PrintStream.println:(Ljava/lang/String;)V
 *   #27 = Class              #29            // java/io/PrintStream
 *   #28 = NameAndType        #30:#31        // println:(Ljava/lang/String;)V
 *   #29 = Utf8               java/io/PrintStream
 *   #30 = Utf8               println
 *   #31 = Utf8               (Ljava/lang/String;)V
 *   #32 = Class              #33            // com/eastern/moon/section8/FieldHashNoPolymorphic
 *   #33 = Utf8               com/eastern/moon/section8/FieldHashNoPolymorphic
 *   #34 = Utf8               Code
 *   #35 = Utf8               LineNumberTable
 *   #36 = Utf8               LocalVariableTable
 *   #37 = Utf8               this
 *   #38 = Utf8               Lcom/eastern/moon/section8/FieldHashNoPolymorphic;
 *   #39 = Utf8               main
 *   #40 = Utf8               ([Ljava/lang/String;)V
 *   #41 = Utf8               args
 *   #42 = Utf8               [Ljava/lang/String;
 *   #43 = Utf8               guy
 *   #44 = Utf8               Lcom/eastern/moon/section8/FieldHashNoPolymorphic$Father;
 *   #45 = Utf8               SourceFile
 *   #46 = Utf8               FieldHashNoPolymorphic.java
 *   #47 = Utf8               NestMembers
 *   #48 = Utf8               BootstrapMethods
 *   #49 = MethodHandle       6:#50          // REF_invokeStatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
 *   #50 = Methodref          #51.#52        // java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
 *   #51 = Class              #53            // java/lang/invoke/StringConcatFactory
 *   #52 = NameAndType        #24:#54        // makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
 *   #53 = Utf8               java/lang/invoke/StringConcatFactory
 *   #54 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
 *   #55 = String             #56            // This guy has $\u0001
 *   #56 = Utf8               This guy has $\u0001
 *   #57 = Utf8               InnerClasses
 *   #58 = Utf8               Son
 *   #59 = Utf8               Father
 *   #60 = Class              #61            // java/lang/invoke/MethodHandles$Lookup
 *   #61 = Utf8               java/lang/invoke/MethodHandles$Lookup
 *   #62 = Class              #63            // java/lang/invoke/MethodHandles
 *   #63 = Utf8               java/lang/invoke/MethodHandles
 *   #64 = Utf8               Lookup
 * {
 *   public com.eastern.moon.section8.FieldHashNoPolymorphic();
 *     descriptor: ()V
 *     flags: (0x0001) ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 8: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/eastern/moon/section8/FieldHashNoPolymorphic;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=2, args_size=1
 *          0: new           #7                  // class com/eastern/moon/section8/FieldHashNoPolymorphic$Son
 *          3: dup
 *          4: invokespecial #9                  // Method com/eastern/moon/section8/FieldHashNoPolymorphic$Son."<init>":()V
 *          7: astore_1
 *          8: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
 *         11: aload_1
 *         12: getfield      #16                 // Field com/eastern/moon/section8/FieldHashNoPolymorphic$Father.money:I
 *         15: invokedynamic #22,  0             // InvokeDynamic #0:makeConcatWithConstants:(I)Ljava/lang/String;
 *         20: invokevirtual #26                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *         23: return
 *       LineNumberTable:
 *         line 37: 0
 *         line 38: 8
 *         line 39: 23
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      24     0  args   [Ljava/lang/String;
 *             8      16     1   guy   Lcom/eastern/moon/section8/FieldHashNoPolymorphic$Father;
 * }
 * SourceFile: "FieldHashNoPolymorphic.java"
 * NestMembers:
 *   com/eastern/moon/section8/FieldHashNoPolymorphic$Son
 *   com/eastern/moon/section8/FieldHashNoPolymorphic$Father
 * BootstrapMethods:
 *   0: #49 REF_invokeStatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
 *     Method arguments:
 *       #55 This guy has $\u0001
 * InnerClasses:
 *   static #58= #7 of #32;                  // Son=class com/eastern/moon/section8/FieldHashNoPolymorphic$Son of class com/eastern/moon/section8/FieldHashNoPolymorphic
 *   static #59= #17 of #32;                 // Father=class com/eastern/moon/section8/FieldHashNoPolymorphic$Father of class com/eastern/moon/section8/FieldHashNoPolymorphic
 *   public static final #64= #60 of #62;    // Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
 * @author moon
 * @date 2023/10/17  23:05
 */
public class FieldHashNoPolymorphic {

    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney(); // 多态
        }

        public void showMeTheMoney() {
            System.out.println("I am Father, i have $" + money);
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        public void showMeTheMoney () {
            System.out.println("I am Son, i have $" + money); // 0  4
        }
    }

    public static void main(String[] args) {
        Father guy = new Son();
        System.out.println("This guy has $" + guy.money);
    }
}
