package com.darian.common.swaggerenum.controller;

import com.darian.common.swaggerenum.module.TestRequest;
import com.darian.common.swaggerenum.module.TestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:59
 */
@Api("测试--控制器")
@RestController
public class RestTestController {

    @ApiOperation("测试方法")
    @GetMapping("/test")
    public TestResponse test(@RequestBody  TestRequest request) {
        return new TestResponse();
    }
}
