package com.darian.aop.logger.util.test;

import com.darian.aop.logger.util.constant.AopLoggerConstants;
import com.darian.aop.logger.util.test.mapper.UserMapper;
import com.darian.aop.logger.util.configuration.EnableAOPLogger;
import com.darian.aop.logger.util.test.controller.TestController;
import com.darian.aop.logger.util.test.mapper.SecurityMapper;
import com.darian.aop.logger.util.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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
@EnableAOPLogger(multipleLoggerFile = false, baseMapperAspect = true)
public class TestAOPApplication {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

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
        LOGGER.info(testController.test(new TestController.ModuleRequest("xxxxxxxx")).toString());
        ;
        try {
            testController.testError();
        } catch (Exception e) {
            System.err.println("error:" + e.getMessage());
        }
        userMapper.selectById(1L);

        MDC.put(AopLoggerConstants.SHADOW_MDC_KEY, "true");

        LOGGER.info(testController.test(new TestController.ModuleRequest("xxxxxxxx")).toString());
    }
}
