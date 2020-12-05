package com.darian.spring.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TestAutowiredMapApplication implements Aware {

    public static Map<String, IPay> beanNamesForType = new HashMap<>();

    @Autowired
    private Map<String, IPay> payMap;

    @Autowired
    private List<IPay> payList;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestAutowiredMapApplication.class, args);
        System.out.println("beanNamesForType: " + beanNamesForType);

        TestAutowiredMapApplication testAutowiredMapApplication = applicationContext.getBean(TestAutowiredMapApplication.class);
        System.out.println("testAutowiredMapApplication: "+ testAutowiredMapApplication);
    }


    @Override
    public String toString() {
        return "TestAutowiredMapApplication{" +
                "\n     payMap=" + payMap +
                "\n     , payList=" + payList +
                '}';
    }
}


@Service("ali")
class Prepare implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.getBeansOfType(IPay.class)
                .values()
                .forEach(iPay -> {
                    IPay put = TestAutowiredMapApplication.beanNamesForType.put(iPay.getType(), iPay);
                    if (put != null) {
                        throw new BeanInitializationException(
                                "The Ipay interface does not allow two implementation classes to contain the same biztype: "
                                        + iPay.getType());
                    }
                });
        System.out.println(applicationContext.getBeansOfType(IPay.class));
    }

}

interface IPay {
    String getType();
}

@Service("AliPay-")
class AliPay implements IPay {

    @Override
    public String getType() {
        return PayType.AliPay.getBiztype();
    }

}

@Service("WeChatPay-")
class WeChatPay implements IPay {

    @Override
    public String getType() {
        return PayType.WeChatPay.getBiztype();
    }
}

enum PayType {
    AliPay("Ali"),
    WeChatPay("WeChat");

    private String biztype;

    PayType(String biztype) {
        this.biztype = biztype;
    }

    public final String getBiztype() {
        return biztype;
    }
}

