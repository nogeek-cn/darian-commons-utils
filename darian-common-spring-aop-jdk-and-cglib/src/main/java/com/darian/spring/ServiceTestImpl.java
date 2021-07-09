package com.darian.spring;

import com.darian.spring.annotation.ServiceLogger;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  上午1:01
 */
@ServiceLogger
@Service
public class ServiceTestImpl {

    @ServiceLogger
    public int test() {
        System.out.println("ServiceTestImpl test");
        return 4;
    }
}
