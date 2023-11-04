package com.eastern.moon.section2;

/**
 * 栈容量 -Xss256k
 * 1. 线程请求栈深度超过虚拟机允许堆最大深度:StackOverflowError
 * 2. 虚拟机栈允许动态扩展（HotSpot不支持），扩展栈容量无法申请到足够内存时：
 * @author moon
 * @date 2023/10/1  15:28
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
