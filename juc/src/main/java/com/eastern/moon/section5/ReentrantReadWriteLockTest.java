package com.eastern.moon.section5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author moon
 */
public class ReentrantReadWriteLockTest {
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock wl = reentrantReadWriteLock.writeLock();
    static Lock rl = reentrantReadWriteLock.readLock();
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                rl.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    /*rl.lock();
                    try {
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getName() + " end");
                    } finally {
                        rl.unlock();
                    }*/
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    rl.unlock();
                }
            }
        }, "rl_thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                rl.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    rl.unlock();
                }
            }
        }, "rl_thread3").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                rl.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    rl.unlock();
                }
            }
        }, "rl_thread4").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wl.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    wl.unlock();
                }
            }
        }, "w_thread2").start();
    }
}
