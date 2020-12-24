package com.darian.chain.configuration;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/24  23:49
 */
@Configuration
public class TaskTypeConfiguration {

    // 配置中心配置 业务 -> 处理器链
    Map<String, List<String>> taskTypeConfiguration = new HashMap<>();
}
