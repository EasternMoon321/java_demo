package com.eastern.moon.section2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 * @author moon
 * @date 2023/10/2  17:49
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        // 不受-XX:MaxDirectMemorySize=10M控制
        /*Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }*/
        // 受-XX:MaxDirectMemorySize=10M控制
        List<ByteBuffer> list = new ArrayList<>();
        while (true) {
            list.add(ByteBuffer.allocateDirect(_1MB));
        }
    }
}
