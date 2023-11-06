package headfirst.designpatterns.decorator.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Application {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/moon/Downloads/ideaIU-2023.1.2-aarch64.dmg");
        byte[] arr = new byte[fileInputStream.available()];
        long time = System.currentTimeMillis();
        fileInputStream.read(arr, 0, fileInputStream.available());
        time = System.currentTimeMillis() - time;
        System.out.println(time);

        time = System.currentTimeMillis();
        fileInputStream.read(arr, 0, fileInputStream.available());
        time = System.currentTimeMillis() - time;
        System.out.println(time);

        time = System.currentTimeMillis();
        fileInputStream.read(arr, 0, fileInputStream.available());
        time = System.currentTimeMillis() - time;
        System.out.println(time);

        System.out.println("--------------");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        arr = new byte[bufferedInputStream.available()];
        time = System.currentTimeMillis();
        bufferedInputStream.read(arr, 0, bufferedInputStream.available());
        time = System.currentTimeMillis() - time;
        System.out.println(time);

        time = System.currentTimeMillis();
        bufferedInputStream.read(arr, 0, bufferedInputStream.available());
        time = System.currentTimeMillis() - time;
        System.out.println(time);

        time = System.currentTimeMillis();
        bufferedInputStream.read(arr, 0, bufferedInputStream.available());
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }
}
