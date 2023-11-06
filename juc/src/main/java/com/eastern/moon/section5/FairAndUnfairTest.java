package com.eastern.moon.section5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * 公平锁保障了FIFO原则
 * 非公平锁减少了线程切换
 * @author moon
 */
public class FairAndUnfairTest {
    private static final int NUM_THREAD = 3;
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);

    public static void main(String[] args) {
//        testLock(fairLock);
        testLock(fairLock);
    }

    private static void testLock(Lock lock) {
        for (int i = 0; i < NUM_THREAD; i++) {
            Job job = new Job(lock);
            job.setName(String.valueOf(i));
            job.start();
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
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Lock by " + Thread.currentThread().getName() +
                        ", Waiting by " + ((ReentrantLock2) lock).getQueuedThreads().stream().map(Thread::getName).collect(Collectors.toList()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Lock by " + Thread.currentThread().getName() +
                        ", Waiting by " + ((ReentrantLock2) lock).getQueuedThreads().stream().map(Thread::getName).collect(Collectors.toList()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
