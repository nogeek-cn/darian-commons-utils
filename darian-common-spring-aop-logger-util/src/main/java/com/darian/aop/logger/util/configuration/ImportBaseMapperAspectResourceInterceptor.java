package com.darian.aop.logger.util.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:META-INF/spring/basemapper-aspect.xml"})
public class ImportBaseMapperAspectResourceInterceptor {
}
