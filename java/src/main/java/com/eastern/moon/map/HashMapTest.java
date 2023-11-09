package com.eastern.moon.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moon
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");
        hashMap.put("null", null);
        hashMap.put(null, "null");
        hashMap.put(null, null);
        System.out.println(hashMap);
    }
}
