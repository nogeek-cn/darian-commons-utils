package com.darian.spring.filterAddMapping;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@ServletComponentScan
public class FilterAddMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterAddMappingApplication.class, args);
    }

}


@WebFilter(urlPatterns = "/*", filterName = "CustomerFilter")
class CustomerFilter implements Filter, ApplicationContextAware {
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AAFilter init");
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
            RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
            HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();

            RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();

            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            String requestUrl = patternsCondition.getPatterns().toString();

            String controllerName = mappingInfoValue.getBeanType().toString();
            String requestMethodName = mappingInfoValue.getMethod().getName();
            Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();

            System.out.println("requestUrl: " + requestUrl);
            System.out.println("controllerName: " + controllerName);
            System.out.println("requestMethodName: " + requestMethodName);

        }

        System.out.println("---");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("CustomerFilter dofilter ...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);

        System.out.println("applicationContext init");
        System.out.println("handlerMappings: " + requestMappingHandlerMapping);
    }
}


@RestController
class TestController {

    @RequestMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String index() {

        return testMsg(null);
    }

    @Value("${server.port:8080}")
    private String server_port;

    @RequestMapping(value = "{msg}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String testMsg(@PathVariable(required = false) String msg) {

        String values = "{\n"
                + "    \"msg\":\"hello, you msg is 【" + msg + "】\",\n"
                + "    \"url\":\"url:" + server_port + "/" + msg + "\"\n"
                + "}";

        return Optional.of(values).orElse("{\n"
                + "    \"msg\":\"hello, you msg is 【" + null + "】\",\n"
                + "    \"url\":\"url:" + server_port + "/" + msg + "\"\n"
                + "}");
    }
}
