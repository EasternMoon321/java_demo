package com.eastern.moon.section5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author moon
 */
public class FairAndUnfairTest {
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);

    @Test
    public void fair() {
        testLock(fairLock);
    }
    @Test
    public void unfair() {
        testLock(unfairLock);
    }
    private void testLock(Lock lock) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Job(lock),"Thread_" + i).start();
        }
    }

    private static class Job extends Thread {
        private Lock lock;
        public Job(Lock lock) {
            this.lock = lock;
        }
        public void run() {
            lock.lock();
            try {
                System.out.println("CurrentThread" + Thread.currentThread().getName());
                System.out.println("QueuedThread" + ((ReentrantLock2) lock).getQueuedThreads());
            } finally {
                lock.unlock();
            }

            lock.lock();
            try {
                System.out.println("CurrentThread" + Thread.currentThread().getName());
                System.out.println("QueuedThread" + ((ReentrantLock2) lock).getQueuedThreads());
            } finally {
                lock.unlock();
            }
        }
    }

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }
        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}
