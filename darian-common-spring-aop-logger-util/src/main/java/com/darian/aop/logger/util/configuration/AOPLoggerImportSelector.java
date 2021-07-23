package com.darian.aop.logger.util.configuration;

import com.darian.aop.logger.util.filter.TraceMDCFilter;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;


/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:02
 */
public class AOPLoggerImportSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableAOPLogger.class.getName(), true));

        Boolean enable = attributes.getBoolean("enable");
        if (!enable) {
            return new String[]{};
        }

        List<String> enableClassNameList = new ArrayList<>();
        enableClassNameList.add(ImportAspectResourceInterceptor.class.getName());
        enableClassNameList.add(AopLoggerProperties.class.getName());

        Boolean multipleLoggerFile = attributes.getBoolean("multipleLoggerFile");
        AOPLoggerLogbackContext.initLogContext(multipleLoggerFile);

        Boolean baseMapperAspect = attributes.getBoolean("baseMapperAspect");
        if (baseMapperAspect) {
            enableClassNameList.add(ImportBaseMapperAspectResourceInterceptor.class.getName());
        }

        boolean traceMDCFilter = attributes.getBoolean("traceMDCFilter");
        if (traceMDCFilter) {
            enableClassNameList.add(TraceMDCFilter.class.getName());
        }

        return  enableClassNameList.toArray(new String[0]);
    }


}
