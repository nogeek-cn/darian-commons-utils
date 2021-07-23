package com.darian.aop.logger.util.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:01
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AOPLoggerImportSelector.class)
@Configuration
public @interface EnableAOPLogger {

    boolean enable() default true;

    boolean multipleLoggerFile() default false;

    boolean baseMapperAspect() default true;

    boolean traceMDCFilter() default true;
}
