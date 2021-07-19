package com.darian.spring.controller;


import com.darian.spring.annotation.ControllerLogger;
import com.darian.spring.service.TestService;
import com.darian.spring.service.TestCacheService;
import com.darian.spring.service.TestRemoteCallService;
import com.darian.spring.service.TestRemoteProviderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:02
 */
@RestController
@ControllerLogger
public class TestController {

    @Resource
    private TestService serviceTest;

    @Resource
    private TestCacheService testCacheService;

    @Resource
    private TestRemoteCallService testRemoteCallService;

    @Resource
    private TestRemoteProviderService testRemoteProviderService;


    @GetMapping("/test")
    public ModuleResponse test(ModuleRequest request) {
        serviceTest.test("xxfssdfs");
        testCacheService.test("sfrtrtrr");
        testRemoteCallService.test("fgdg");
        testRemoteProviderService.test("sdfsd");
        return new ModuleResponse("test");
    }

    @GetMapping("/testError")
    public void testError() {
        throw new RuntimeException("testError");
    }

    @Data
    @AllArgsConstructor
    public static class ModuleRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class ModuleResponse {
        private String name;
    }
}
