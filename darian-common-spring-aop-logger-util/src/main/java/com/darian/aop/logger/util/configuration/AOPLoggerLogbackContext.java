package com.darian.aop.logger.util.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import org.slf4j.impl.StaticLoggerBinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AOPLoggerLogbackContext {

    private static final String CLASSPATH_PREFIX = "classpath:";



    private static final String AOP_LOGGER_SINGLE_SPRING_XML = "classpath:META-INF/logback/aop-logger-single-spring.xml";

    private static final String AOP_LOGGER_MULTIPLE_SPRING_XML = "classpath:META-INF/logback/aop-logger-multiple-spring.xml";

    public static void initLogContext(Boolean multipleLoggerFile) {
        if (multipleLoggerFile) {
            initLogContext(AOP_LOGGER_MULTIPLE_SPRING_XML);
        } else {
            initLogContext(AOP_LOGGER_SINGLE_SPRING_XML);
        }
    }

    public static void initLogContext(String location) {
        try {
            LoggerContext loggerContext = (LoggerContext) StaticLoggerBinder.getSingleton().getLoggerFactory();
            new ContextInitializer(loggerContext).configureByResource(getResourceUrl(location));
        } catch (Exception e) {
            throw new IllegalStateException("Could not initialize Logback Nacos logging from " + location, e);
        }
    }


    public static URL getResourceUrl(String resource) throws IOException {
        if (resource.startsWith(CLASSPATH_PREFIX)) {
            String path = resource.substring(CLASSPATH_PREFIX.length());

            ClassLoader classLoader = AOPLoggerImportSelector.class.getClassLoader();

            URL url = (classLoader != null ? classLoader.getResource(path) : ClassLoader.getSystemResource(path));
            if (url == null) {
                throw new FileNotFoundException("Resource [" + resource + "] does not exist");
            }

            return url;
        }

        try {
            return new URL(resource);
        } catch (MalformedURLException ex) {
            return new File(resource).toURI().toURL();
        }
    }
}
