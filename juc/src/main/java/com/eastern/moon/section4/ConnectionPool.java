package com.eastern.moon.section4;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author moon
 * @date 2023/10/28  15:32
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();
    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0 ; i < initialSize; ++i) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                // 当有连接时，通知其他等待线程
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            // 允许超时
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                // 条件不满足推出
                while (pool.isEmpty() && remaining > 0) {
                    // 等待remaining秒
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
