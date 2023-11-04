package com.eastern.moon.section4;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 * @author moon
 * @date 2023/10/14  22:52
 */
public class JConsole_MemoryTest {

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; ++i) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }

    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(1000);
        fillHeap(1000);
        System.gc();
        Thread.sleep(600* 1000);
    }
}
