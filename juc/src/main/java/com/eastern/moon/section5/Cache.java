package com.eastern.moon.section5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author moon
 */
public class Cache {
    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();
    static Lock r = rw1.readLock();
    static Lock w = rw1.writeLock();
    public static Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }
    public static Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }
    public static void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
