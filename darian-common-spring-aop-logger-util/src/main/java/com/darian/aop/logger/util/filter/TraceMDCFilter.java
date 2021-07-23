package com.darian.aop.logger.util.filter;

import com.darian.aop.logger.util.configuration.AopLoggerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class TraceMDCFilter extends OncePerRequestFilter implements Ordered,
        ApplicationContextAware {

    private AopLoggerProperties aopLoggerProperties;

    private static Logger LOGGER = LoggerFactory.getLogger(TraceMDCFilter.class);

    /**
     * 必须比你们生成 traceId 的地方后边，优先级低，所以 + 1
     *
     * @return
     */
    @Override
    public int getOrder() {
        if (aopLoggerProperties == null) {
            LOGGER.warn("aopLoggerProperties == null");
            return 2147483638;
        }
        int traceMDCFilterOrder = aopLoggerProperties.getTraceMDCFilterOrder();
        LOGGER.info("TraceMDCFilter order " + traceMDCFilterOrder);
        return traceMDCFilterOrder;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        if (aopLoggerProperties == null) {
            LOGGER.warn("aopLoggerProperties == null");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String traceId = httpServletRequest.getHeader(aopLoggerProperties.getTraceIdHttpHeader());
        if (traceId == null || "".equals(traceId)) {
            LOGGER.warn("traceId is black");
            traceId = UUID.randomUUID().toString().replace("-", "");
        }
        String parentApp = httpServletRequest.getHeader(aopLoggerProperties.getParentAppNameHttpHeader());

        MDC.put(aopLoggerProperties.getTraceIdMdcKey(), traceId);
        MDC.put(aopLoggerProperties.getParentAppNameMdcKey(), parentApp);
        // TODO: rpcId, hostIp
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        aopLoggerProperties = applicationContext.getBean(AopLoggerProperties.class);
    }
}
