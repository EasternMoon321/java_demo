package com.eastern.moon.section4;

import java.util.concurrent.TimeUnit;

/**
 * @author moon
 */
public class DefaultThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        DefaultThreadPool<Runnable> threadPool = new DefaultThreadPool<>(5);
        TimeUnit.SECONDS.sleep(1);
        threadPool.shutDown();
        TimeUnit.SECONDS.sleep(1);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("1 = 1");
            }
        });
    }
}
