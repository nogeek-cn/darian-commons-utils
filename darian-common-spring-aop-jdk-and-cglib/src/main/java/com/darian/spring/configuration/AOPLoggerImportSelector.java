package com.darian.spring.configuration;

import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:02
 */
public class AOPLoggerImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
        System.out.println(annotationTypes);
        return new String[]{ImportResourceDalInterceptor.class.getName()};
//        return new String[]{};

//        return new String[2]{"classpath:META-INF/spring/dal-interceptor-bean.xml",
//                "classpath:META-INF/spring/service-aspect.xml"};
    }
}
