package com.darian.spring;

import com.darian.spring.configuration.EnableAOPLogger;
import com.darian.spring.controller.TestController;
import com.darian.spring.mapper.SecurityMapper;
import com.darian.spring.mapper.UserMapper;
import com.darian.spring.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  下午21:59
 */
@SpringBootApplication
@EnableAOPLogger
public class TestAOPApplication {

    @Resource
    private TestService testService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private SecurityMapper securityMapper;

    @Resource
    private TestController testController;

    public static void main(String[] args) {
        SpringApplication.run(TestAOPApplication.class, args);
    }

    @PostConstruct
    public void test() {
        testController.test(new TestController.ModuleRequest("xxxxxxxx"));
        try {
            testController.testError();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userMapper.selectById(1L);
    }
}
