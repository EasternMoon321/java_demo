package com.eastern.moon.section2;

import java.util.ArrayList;

/**
 *
 * 堆最大值 -Xms20m
 * 堆最小值 -Xmx20m
 * 内存溢出时，dump当前内存堆转存快照 -XX:+HeapDumpOnOutOfMemoryError
 * @author moon
 * @date 2023/10/1 15:04
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
