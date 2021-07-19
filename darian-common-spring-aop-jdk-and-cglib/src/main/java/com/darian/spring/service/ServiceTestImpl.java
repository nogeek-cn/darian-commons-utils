package com.darian.spring.service;

import com.darian.spring.annotation.ServiceLogger;
import com.darian.spring.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.plaf.PanelUI;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  下午23:01
 */
@Service
public class ServiceTestImpl {

    @Resource
    private UserMapper userMapper;

    @ServiceLogger
    public String test(String name) {
        userMapper.selectByIdXXXXX(1L);
        return name + "-ServiceTestImpl";
    }
}
