package com.darian.spring.interceptor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.xml.internal.ws.encoding.DataHandlerDataSource;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.naming.factory.DataSourceLinkFactory;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.config.TransactionManagementConfigUtils;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.Objects;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  上午12:47
 */
public class DALInterceptor implements MethodInterceptor {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Before DALInterceptor");
        long start = System.currentTimeMillis();
        Method method = methodInvocation.getMethod();
        String methodName = method.getName();
        Class<?> declaringClass = method.getDeclaringClass();
        String classSimpleName = declaringClass.getSimpleName();

        try {
            Object proceed = methodInvocation.proceed();
            return proceed;
        } finally {
            System.out.println("After DALInterceptor");
            long end = System.currentTimeMillis();
            LOGGER.info(classSimpleName + "-" + methodName + ":" + (end - start) + "ms");
        }
    }
}
