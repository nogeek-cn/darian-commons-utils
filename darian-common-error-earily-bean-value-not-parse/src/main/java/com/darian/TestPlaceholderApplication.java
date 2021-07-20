package com.darian;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/4/27  上午1:58
 */
@SpringBootApplication
public class TestPlaceholderApplication {

    @Bean
    public String activeProfiles(@Value("${spring.profiles.active}") String activeProfiles) {
        return activeProfiles;
    }


    @Bean("SpringActiveProfilesBeanFactoryPostProcessor")
    public SpringActiveProfilesBeanFactoryPostProcessor
    springActiveProfilesBeanFactoryPostProcessor(String activeProfiles) {
        return new SpringActiveProfilesBeanFactoryPostProcessor(activeProfiles);
    }


    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");
        ConfigurableApplicationContext run = SpringApplication.run(TestPlaceholderApplication.class, args);
        System.out.println(run.getBean("activeProfiles"));
        try {
            System.out.println(run.getBean("SpringActiveProfilesBeanFactoryPostProcessor", SpringActiveProfilesBeanFactoryPostProcessor.class)
                    .getActiveProfiles());
            System.out.println(run.getBean("SpringActiveProfilesBeanFactoryPostProcessor_1", SpringActiveProfilesBeanFactoryPostProcessor.class)
                    .getActiveProfiles());
        } catch (Exception e) {
        }

    }


    public static class SpringActiveProfilesBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

        private String activeProfiles;

        public SpringActiveProfilesBeanFactoryPostProcessor(String activeProfiles) {
            this.activeProfiles = activeProfiles;
        }

        public String getActiveProfiles() {
            return activeProfiles;
        }

        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        }
    }


    @Bean("SpringActiveProfilesBeanFactoryPostProcessor_1")
    public SpringActiveProfilesBeanFactoryPostProcessor
    springActiveProfilesBeanFactoryPostProcessor_1(String activeProfiles, Environment environment) {
        return new SpringActiveProfilesBeanFactoryPostProcessor(environment.resolvePlaceholders(activeProfiles));
    }
}
