package com.darian.aop.logger.util.test.service;

import com.darian.aop.logger.util.annotation.CacheLogger;
import org.springframework.stereotype.Service;

@Service
@CacheLogger
public class TestCacheService {

    public String test(String name) {
        return name + "-TestCacheService";
    }
}
