package com.eastern.moon.section5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author moon
 */
public class ProcessDataTest {

    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = reentrantReadWriteLock.readLock();
    static Lock writeLock = reentrantReadWriteLock.writeLock();
    static volatile boolean update;

    public static void main(String[] args) {

    }

    public void processData() {
        readLock.lock();
        if (!update) {
            readLock.unlock();

            // 锁降级从写锁获取开始
            writeLock.lock();
            try {
                if (!update) {
                    update = true;
                }
                // 读锁保证数据可见性
                readLock.lock();
            } finally {
                writeLock.unlock();
            }
            // 锁降级完成，写锁降级为读锁
        }
        try {
            System.out.println(update);
        } finally {
            readLock.unlock();
        }
    }
}
