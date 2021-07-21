package com.darian.aop.logger.util.test;

import com.darian.aop.logger.util.configuration.AopLoggerProperties;
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
@EnableAOPLogger(multipleLoggerFile = true, baseMapperAspect = true)
public class TestAOPApplication {

    private Logger LOGGER = LoggerFactory.getLogger(TestAOPApplication.class);

    @Resource
    private TestService testService;

    @Resource
    private AopLoggerProperties aopLoggerProperties;

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
        System.out.println();
        LOGGER.error("开始测试");

        LOGGER.error("正常流量 无 traceId, rpcId, parentAppName, parentHostIp:");
        testController.test(new TestController.ModuleRequest("aaa"));
        try {
            testController.testError();
        } catch (Exception e) {
        }

        LOGGER.error("正常流量，有 traceId, rpcId, parentAppName, parentHostIp:");
        MDC.put(aopLoggerProperties.getTraceIdMdcKey(), "mockTraceId");
        MDC.put(aopLoggerProperties.getRpcIdMdcKey(), "mockRpcId");
        MDC.put(aopLoggerProperties.getParentAppNameMdcKey(), "mockParentAppName");
        MDC.put(aopLoggerProperties.getParentHostIpMdcKey(), "mockParentHostIp");
        testController.test(new TestController.ModuleRequest("bbb"));

        LOGGER.error("压测流量，有 traceId, rpcId, parentAppName, parentHostIp:");
        MDC.put(aopLoggerProperties.getShadowMdcKey(), "true");
        MDC.put(aopLoggerProperties.getTraceIdMdcKey(), "shadowMockTraceId");
        MDC.put(aopLoggerProperties.getRpcIdMdcKey(), "shadowMockRpcId");
        MDC.put(aopLoggerProperties.getParentAppNameMdcKey(), "shadowMockParentAppName");
        MDC.put(aopLoggerProperties.getParentHostIpMdcKey(), "shadowMockParentHostIp");
        testController.test(new TestController.ModuleRequest("ccc"));

        LOGGER.error("测试结束");

    }
}
