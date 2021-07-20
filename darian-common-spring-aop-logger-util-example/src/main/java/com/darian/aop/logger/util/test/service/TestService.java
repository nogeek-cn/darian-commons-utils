package com.darian.aop.logger.util.test.service;

import com.darian.aop.logger.util.test.mapper.UserMapper;
import com.darian.aop.logger.util.annotation.ServiceLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  下午23:01
 */
@Service
public class TestService {

    @Resource
    private UserMapper userMapper;

    @ServiceLogger
    public String test(String name) {
        userMapper.selectById(1L);
        userMapper.selectByIdXXXXX(1L);
        return name + "-TestService";
    }
}
