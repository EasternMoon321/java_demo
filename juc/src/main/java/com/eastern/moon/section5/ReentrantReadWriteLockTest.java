package com.eastern.moon.section5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author moon
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLock.readLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    reentrantReadWriteLock.readLock().unlock();
                }
            }
        }, "thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLock.readLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    reentrantReadWriteLock.readLock().unlock();
                }
            }
        }, "thread3").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLock.writeLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
        }, "thread2").start();
    }
}
