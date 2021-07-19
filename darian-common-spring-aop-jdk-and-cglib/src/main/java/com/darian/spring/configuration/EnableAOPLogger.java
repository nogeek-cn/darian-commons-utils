package com.darian.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.lang.annotation.*;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:01
 */

//@ImportResource(
//        {"classpath:META-INF/spring/dal-aspect.xml",
//                "classpath:META-INF/spring/service-aspect.xml",
//                "classpath:META-INF/spring/controller-aspect.xml"})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AOPLoggerImportSelector.class)
@Configuration
public @interface EnableAOPLogger {

    boolean enable() default false;
}
