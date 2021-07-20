package com.darian.aop.logger.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DarianSpringApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "test,dev");
//        System.setProperty("spring.profiles.active", "test|dev");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DarianSpringApplication.class, args);
        System.out.println(applicationContext.getBean(String.class));
    }


    @Profile("dev|test")
    @Bean
    public String testString() {
        return new String("dev | test");
    }
}
