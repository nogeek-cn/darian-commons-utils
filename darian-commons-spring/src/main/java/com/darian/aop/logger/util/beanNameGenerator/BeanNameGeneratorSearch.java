package com.darian.aop.logger.util.beanNameGenerator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

public class BeanNameGeneratorSearch {

    public static void main(String[] args) {
        String beanName = getBeanName(BBA.class);
        System.out.println(beanName);
        // beanNameGeneratorSearch.BBA

        // 前两个字母大写直接返回的 ClassName
    }

    public static String getBeanName(Class<?> clazz) {
        BeanDefinition definition = BeanDefinitionBuilder
                .genericBeanDefinition(clazz)
                .getBeanDefinition();
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());

        return Introspector.decapitalize(shortClassName);
    }

    public static class BBA {

    }
}