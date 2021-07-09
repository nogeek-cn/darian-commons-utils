package com.darian.spring.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  上午12:47
 */
public class ServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before ServiceInterceptor");
        Object proceed = invocation.proceed();
        System.out.println("After ServiceInterceptor");
        return proceed;
    }
}
