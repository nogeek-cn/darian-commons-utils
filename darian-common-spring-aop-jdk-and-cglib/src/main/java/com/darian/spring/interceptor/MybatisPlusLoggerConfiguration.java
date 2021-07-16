package com.darian.spring.interceptor;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class MybatisPlusLoggerConfiguration {

    @Resource
    private List<InnerInterceptor> innerInterceptorList;

    @Bean
    public MybatisPlusLoggerInterceptor paginationInterceptor() {
        return new MybatisPlusLoggerInterceptor();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        for (InnerInterceptor innerInterceptor : innerInterceptorList) {
            mybatisPlusInterceptor.addInnerInterceptor(innerInterceptor);
        }
        return mybatisPlusInterceptor;
    }
}
