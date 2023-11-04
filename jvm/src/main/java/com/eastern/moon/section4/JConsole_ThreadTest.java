package com.eastern.moon.section4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author moon
 * @date 2023/10/14  23:19
 */
public class JConsole_ThreadTest {
    /**
     * 线程死循环
     */
    public static void createBusyThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                }
            }
        }, "testBusyThread").start();
    }

    /**
     * 线程锁等待
     * @param lock
     */
    public static void createLockThread(final Object lock) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread").start();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }
}
