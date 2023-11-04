package com.eastern.moon.section3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author moon
 * @date 2023/10/25  22:20
 */
public class ReentrantLockTest {
    int a = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void writer() {
         lock.lock();
        try {
            a++;
            Thread.sleep(1000*60*10);
            System.out.println("a" + a);
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void reader () {
        lock.lock();
        try {
            int i = a;
            System.out.println(i);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();

        Thread writerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.writer();
            }
        }, "writerThread");

        Thread readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.reader();
            }
        }, "readerThread");

        writerThread.start();
        readerThread.start();
    }

}
