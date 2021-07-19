package com.darian.spring.service;

import com.darian.spring.annotation.RemoteCallLogger;
import com.darian.spring.annotation.RemoteProviderLogger;
import org.springframework.stereotype.Service;

@Service
@RemoteCallLogger
public class TestRemoteCallService {

    public String test(String name) {
        return name + "-TestRemoteCallService";
    }
}
