package com.darian.aop.logger.util.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:02
 */
public class AOPLoggerImportSelector implements ImportSelector {



    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableAOPLogger.class.getName());
        Boolean enable = (Boolean) annotationAttributes.get("enable");
        if (!enable) {
            return new String[]{};
        }

        Boolean multipleLoggerFile = (Boolean) annotationAttributes.get("multipleLoggerFile");
        AOPLoggerLogbackContext.initLogContext(multipleLoggerFile);

        Boolean baseMapperAspect = (Boolean) annotationAttributes.get("baseMapperAspect");
        if (baseMapperAspect) {
            return new String[]{ImportBaseMapperAspectResourceInterceptor.class.getName(),
                    ImportAspectResourceInterceptor.class.getName(),
                    AopLoggerProperties.class.getName()};
        } else {
            return new String[]{ImportAspectResourceInterceptor.class.getName(),
                    AopLoggerProperties.class.getName()};
        }

    }



}
