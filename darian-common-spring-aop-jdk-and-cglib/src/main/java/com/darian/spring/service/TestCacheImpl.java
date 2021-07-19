package com.darian.spring.service;

import com.darian.spring.annotation.CacheLogger;
import com.darian.spring.annotation.ServiceLogger;
import org.springframework.stereotype.Service;

@Service
@CacheLogger
public class TestCacheImpl {

    public String test(String name) {
        return name + "-TestCacheImpl";
    }
}
