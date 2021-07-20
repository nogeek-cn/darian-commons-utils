package com.darian.aop.logger.util.configuration;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;


/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:02
 */
public class AOPLoggerImportSelector implements ImportSelector {



    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableAOPLogger.class.getName(), true));

        Boolean enable = attributes.getBoolean("enable");
        if (!enable) {
            return new String[]{};
        }

        Boolean multipleLoggerFile = attributes.getBoolean("multipleLoggerFile");
        AOPLoggerLogbackContext.initLogContext(multipleLoggerFile);

        Boolean baseMapperAspect = attributes.getBoolean("baseMapperAspect");

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
