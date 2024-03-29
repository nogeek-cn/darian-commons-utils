package com.darian.aop.logger.util.interceptor;


import com.darian.aop.logger.util.annotation.DalLogger;
import com.darian.aop.logger.util.constant.AopLoggerConstants;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  下午21:30
 */
public class DalInterceptor extends BaseAbstractLogInterceptor {

    private Logger DAL_LOGGER = LoggerFactory.getLogger(AopLoggerConstants.DAL_LOGGER_NAME);

    private Logger DAL_SHADOW_LOGGER = LoggerFactory.getLogger(AopLoggerConstants.DAL_SHADOW_LOGGER_NAME);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long startTime = System.currentTimeMillis();

        Method method = methodInvocation.getMethod();

        String classSimpleName = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();

        DalLogger dalLogger = methodInvocation.getMethod().getAnnotation(DalLogger.class);

        if (dalLogger == null) {
            dalLogger = methodInvocation.getMethod().getDeclaringClass().getAnnotation(DalLogger.class);
        }

        // 获取参数
        Object[] args = methodInvocation.getArguments();
        //调用是否成功
        boolean isSuccess = true;
        //拦截方法的执行结果
        Object result = null;

        try {
            result = methodInvocation.proceed();
            isSuccess = isSuccess(result);
            return result;
        } catch (Exception e) {
            isSuccess = false;
            templateLoggerError(DAL_LOGGER,
                    DAL_SHADOW_LOGGER,
                    "[LOGGER][DAL][msg]" + e.getMessage(), e);
            throw e;
        } finally {

            // 确保任何情况下业务都能正常进行
            try {
                long endTime = System.currentTimeMillis();
                long costTimes = endTime - startTime;
                templateLoggerInfo(DAL_LOGGER,
                        DAL_SHADOW_LOGGER,
                        defaultConstructLogString(classSimpleName, methodName, isSuccess, costTimes, args, result, dalLogger));
            } catch (Exception e) {
                templateLoggerError(DAL_LOGGER,
                        DAL_SHADOW_LOGGER,
                        "[LOGGER][DAL][msg]" + e.getMessage(), e);
            }
        }
    }

    protected final String defaultConstructLogString(String classSimpleName, String methodName, boolean isSuccess,
                                                     long costTimes, Object[] args, Object result, DalLogger dalLogger) {

        StringBuilder sb = new StringBuilder();
        sb.append(LOG_PARAM_PREFIX);
        sb.append(getAPPContext());
        sb.append(LOG_PARAM_SUFFIX);

        sb.append(LOG_PREFIX);
        sb.append(LOG_PARAM_PREFIX);
        sb.append(classSimpleName);
        sb.append(LOG_SEP_POINT);
        sb.append(methodName);
        sb.append(LOG_SEP);
        sb.append(isSuccess ? YES : NO);
        sb.append(LOG_SEP);
        sb.append(costTimes);
        sb.append(TIME_UNIT);
        sb.append(LOG_PARAM_SUFFIX);

        if (aopLoggerProperties.isDalLoggerRequest()) {
            if (dalLogger != null && dalLogger.needParams()) {
                // 参数已经有 "()" 号了
                sb.append(getMsgOfArgs(args));
            }
        }

        if (aopLoggerProperties.isDalLoggerResponse()) {
            if (dalLogger != null && dalLogger.needResult()) {
                sb.append(LOG_PARAM_PREFIX);
                sb.append(getMsg(result));
                sb.append(LOG_PARAM_SUFFIX);
            }
        }

        sb.append(LOG_SUFFIX);
        return sb.toString().replaceAll("\n", "");
    }


    private boolean isSuccess(Object result) {
        return true;
    }
}
