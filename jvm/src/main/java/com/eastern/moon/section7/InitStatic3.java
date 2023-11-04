package com.eastern.moon.section7;

/**
 * @author moon
 * @date 2023/10/15  03:59
 */
public class InitStatic3 {
    static class DeadLoopClass {
        static {
            if (true) {
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                while (true) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };
        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);

        thread1.start();
        thread2.start();
    }
}
