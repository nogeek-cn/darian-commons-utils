package com.darian.spring.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

@Configuration
public class MybatisPlusLoggerInterceptor implements InnerInterceptor {


    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        System.out.println("xxxxxxx");
    }

}
