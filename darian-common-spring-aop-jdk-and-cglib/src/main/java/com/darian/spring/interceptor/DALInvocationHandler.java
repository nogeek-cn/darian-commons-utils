package com.darian.spring.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  下午12:37
 */
public class DALInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("DALInvocationHandler before");
        Object invoke = method.invoke(proxy, args);
        System.out.println("DALInvocationHandler after");
        return invoke;
    }
}
