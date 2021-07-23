package com.darian.aop.logger.util.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:02
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(value = "darian.aop-log")
public class AopLoggerProperties {

    private Integer maxLength = 500;

    private boolean cacheLoggerRequest = false;

    private boolean cacheLoggerResponse = false;

    private boolean controllerLoggerRequest = false;

    private boolean controllerLoggerResponse = false;

    private boolean dalLoggerRequest = false;

    private boolean dalLoggerResponse = false;

    private boolean remoteCallRequest = false;

    private boolean remoteCallResponse = false;

    private boolean remoteProviderRequest = false;

    private boolean remoteProviderResponse = false;

    private boolean serviceRequest = false;

    private boolean serviceResponse = false;

    /**
     * 压测标记
     */
    private String shadowMdcKey = "SHADOW";

    private String traceIdMdcKey = "traceId";

    private String traceIdHttpHeader = "traceId";

    private int traceMDCFilterOrder = 2147483638;

    private String rpcIdMdcKey = "rpcId";

    private String parentAppNameMdcKey = "parentAppName";

    private String parentAppNameHttpHeader = "parentAppName";

    private String parentHostIpMdcKey = "parentHostIP";

}
