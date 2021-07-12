package com.darian.spring.interceptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  下午12:40
 */
@Configuration
public class MapperBeanPostprocessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
//                bean,
//                new DALInvocationHandler());
        if (beanName.equals("dalMapperImpl")) {
            System.out.println(bean.getClass());
        }

//        System.out.println("MapperBeanPostprocessor"+"-:-"+beanName + "-:-");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
