package com.eastern.moon.section2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * -xx:MaxMetaspaceSize 元空间最大值  -XX:MetaspaceSize 元空间初始大小
 * Jdk 7- Perm 永久代
 * Jdk 8+ MateSpace 元空间
 * @author moon
 * @date 2023/10/2  17:21
 */
public class JavaMethodAreaOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }
}
