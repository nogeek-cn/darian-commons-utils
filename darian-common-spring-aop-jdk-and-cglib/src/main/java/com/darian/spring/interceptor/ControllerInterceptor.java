package com.darian.spring.interceptor;


import com.darian.spring.annotation.ControllerLogger;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  下午23:57
 */
public class ControllerInterceptor extends BaseAbstractLogInterceptor {

    private Logger LOGGER = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long startTime = System.currentTimeMillis();

        Method method = methodInvocation.getMethod();

        String classSimpleName = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();

        ControllerLogger controllerLogger = methodInvocation.getMethod().getAnnotation(ControllerLogger.class);

        if (controllerLogger == null) {
            controllerLogger = methodInvocation.getMethod().getDeclaringClass().getAnnotation(ControllerLogger.class);
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
        } catch (Throwable t) {
            isSuccess = false;
            throw t;
        } finally {

            // 确保任何情况下业务都能正常进行
            try {
                long endTime = System.currentTimeMillis();
                long costTimes = endTime - startTime;
                // TODO:
                LOGGER.info(defaultConstructLogString(classSimpleName, methodName, isSuccess, costTimes, args, result, controllerLogger));
            } catch (Exception e) {
                LOGGER.error("[LOGGER][Controller][msg]", e);
            }
        }
    }

    protected final String defaultConstructLogString(String classSimpleName, String methodName, boolean isSuccess,
                                                     long costTimes, Object[] args, Object result, ControllerLogger controllerLogger) {

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

        // TODO: 全局开关打开
//        if ()

        if (controllerLogger != null && controllerLogger.needParams()) {
            // 参数已经有 "()" 号了
            sb.append(getMsgOfArgs(args));
        }
        // TODO: 全局开关打开

        if (controllerLogger!= null && controllerLogger.needResult()) {
            sb.append(LOG_PARAM_PREFIX);
            sb.append(getMsg(result));
            sb.append(LOG_PARAM_SUFFIX);
        }

        sb.append(LOG_SUFFIX);
        return sb.toString().replaceAll("\n", "");
    }


    private boolean isSuccess(Object result) {
        if (result == null) {
            return false;
        } else {
            // TODO:  自定义成功还是失败
        }
        return true;
    }
}
