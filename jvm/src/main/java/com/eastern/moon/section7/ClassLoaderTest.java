package com.eastern.moon.section7;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author moon
 * @date 2023/10/15  04:14
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("com.eastern.moon.section7.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.eastern.moon.section7.ClassLoaderTest);
        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
        System.out.println(classLoaderTest instanceof com.eastern.moon.section7.ClassLoaderTest);
        System.out.println(ClassLoader.getSystemClassLoader());
        Object o = ClassLoader.getSystemClassLoader().loadClass("com.eastern.moon.section7.ClassLoaderTest").newInstance();
        System.out.println(o instanceof com.eastern.moon.section7.ClassLoaderTest);
    }
}
