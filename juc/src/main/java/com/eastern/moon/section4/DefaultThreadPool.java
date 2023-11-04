package com.eastern.moon.section4;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author moon
 * @date 2023/10/28  15:30
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private static final int MAX_WORKER_NUMBERS = 10;
    private static final int DEFAULT_WORKER_NUMBERS= 5;
    private static final int MIN_WORKER_NUMBERS = 1;
    /**
     * 任务队列
     */
    private final LinkedList<Job> jobs = new LinkedList<>();
    /**
     * 工作队列
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    private final Map<Worker, Thread> workerThreadMap = Collections.synchronizedMap(new HashMap<Worker, Thread>());
    /**
     * 工作线程数
     */
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    private AtomicLong threadNum = new AtomicLong();
    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : Math.max(num, MIN_WORKER_NUMBERS);
        initializeWorkers(workerNum);
    }
    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                // 向工作队列中添加任务
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutDown() {
        for (Worker worker : workers) {
            // 没有打断？
            worker.shutdown();
            Thread thread = workerThreadMap.get(worker);
            thread.interrupt();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }



    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }



    @Override
    public int getJobSize() {
        return jobs.size();
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            workerThreadMap.put(worker, thread);
            thread.start();
        }
    }

    class Worker implements Runnable {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
