package com.darian.aop.logger.util.test.service;

import com.darian.aop.logger.util.annotation.RemoteCallLogger;
import org.springframework.stereotype.Service;

@Service
@RemoteCallLogger
public class TestRemoteCallService {

    public String test(String name) {
        return name + "-TestRemoteCallService";
    }
}
