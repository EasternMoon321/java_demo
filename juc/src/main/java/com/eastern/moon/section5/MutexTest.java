package com.eastern.moon.section5;

import java.util.concurrent.TimeUnit;

/**
 * @author moon
 */
public class MutexTest {
    public static void main(String[] args) throws InterruptedException {
        Mutex mutex = new Mutex();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mutex.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    TimeUnit.MINUTES.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.unlock();
                }
            }
        }, "thread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mutex.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    System.out.println(Thread.currentThread().getName() + " end");
                } finally {
                    mutex.unlock();
                }
            }
        }, "thread2");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                mutex.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    System.out.println(Thread.currentThread().getName() + " end");
                } finally {
                    mutex.unlock();
                }
            }
        }, "thread3");

        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
    }
}
