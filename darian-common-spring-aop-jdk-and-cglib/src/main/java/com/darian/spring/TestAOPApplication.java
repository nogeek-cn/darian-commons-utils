package com.darian.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  上午12:32
 */
@SpringBootApplication
@ImportResource({"classpath:META-INF/spring/*.xml"})
public class TestAOPApplication {

    @Resource
    private DalMapper dalMapper;

    @Resource
    private ServiceTestImpl serviceTestImpl;

    public static void main(String[] args) {
        SpringApplication.run(TestAOPApplication.class, args);
    }

    @PostConstruct
    public void test() {
        dalMapper.test();
        serviceTestImpl.test();
    }
}