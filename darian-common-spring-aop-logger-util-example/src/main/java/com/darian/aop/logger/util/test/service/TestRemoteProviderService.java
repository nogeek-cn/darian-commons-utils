package com.darian.aop.logger.util.test.service;

import com.darian.aop.logger.util.annotation.RemoteProviderLogger;
import org.springframework.stereotype.Service;

@Service
@RemoteProviderLogger
public class TestRemoteProviderService {

    public String test(String name) {
        return name + "-TestRemoteProviderService";
    }
}
