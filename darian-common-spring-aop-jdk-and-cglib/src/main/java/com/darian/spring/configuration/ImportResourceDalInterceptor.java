package com.darian.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:25
 */
@Configuration
@ImportResource(
        {"classpath:META-INF/spring/dal-aspect.xml",
                "classpath:META-INF/spring/service-aspect.xml",
                "classpath:META-INF/spring/controller-aspect.xml",
                "classpath:META-INF/spring/cache-aspect.xml",
                "classpath:META-INF/spring/remote-call-aspect.xml",
                "classpath:META-INF/spring/remote-provider-aspect.xml"})
public class ImportResourceDalInterceptor {
}
