package com.darian.spring.interceptor;


import com.darian.spring.configuration.AopLoggerProperties;
import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  下午22:01
 */
public abstract class BaseAbstractLogInterceptor implements MethodInterceptor {

    private Logger LOGGER = LoggerFactory.getLogger(BaseAbstractLogInterceptor.class);

    @Resource
    protected AopLoggerProperties aopLoggerProperties;


    /**
     * 日志默认值
     */
    protected static final String LOG_DEFAULT = "-";

    /**
     * 日志前缀
     */
    protected static final String LOG_PREFIX = "[";

    /**
     * 日志后缀
     */
    protected static final String LOG_SUFFIX = "]";

    /**
     * 日志参数前缀
     */
    protected static final String LOG_PARAM_PREFIX = "(";

    /**
     * 日志参数后缀
     */
    protected static final String LOG_PARAM_SUFFIX = ")";

    /**
     * 日志分隔符(英文分号)
     */
    protected static final String LOG_SEP = ",";

    /**
     * 日志分隔符(英文点号)
     */
    protected static final String LOG_SEP_POINT = ".";

    /**
     * 成功
     */
    protected static final String YES = "Y";

    /**
     * 失败
     */
    protected static final String NO = "N";

    /**
     * 时间
     */
    protected static final String TIME_UNIT = "ms";

    /**
     * @param logger
     * @param shadowLogger
     * @param msg
     */
    protected void templateLoggerInfo(Logger logger, Logger shadowLogger, String msg) {
        if (msg.length() > aopLoggerProperties.getMaxLength()) {
            msg = msg.substring(0, aopLoggerProperties.getMaxLength()) + ")]";
        }
        if (isShadow()) {
            shadowLogger.info(msg);
        } else {
            logger.info(msg);
        }
    }

    /**
     * @param logger
     * @param shadowLogger
     * @param msg
     */
    protected void templateLoggerError(Logger logger, Logger shadowLogger, String msg, Exception e) {
        if (isShadow()) {
            shadowLogger.error(msg, e);
        } else {
            logger.error(msg, e);
        }
    }

    /**
     * 是否是压测流量
     *
     * @return
     */
    protected boolean isShadow() {
        // TODO: 是否是压测流量
        return false;
    }

    protected String getAPPContext() {
        // TODO: 获取上下文, TranceId 等等 MDC
        return "traceId,rpcId,parentAppName,parentHostIP";
    }


    protected final String getMsgOfArgs(Object[] objects) {

        if (objects == null) {
            return LOG_PARAM_PREFIX + LOG_DEFAULT + LOG_PARAM_SUFFIX;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(LOG_PARAM_PREFIX);
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null && objects[i].getClass().isAnonymousClass()) {
                //如果是匿名类
                sb.append(getMsgOfAnonymous(objects[i]));
            } else {
                sb.append(getMsg(objects[i]));
            }

            //拼接逗号
            if (i != objects.length - 1) {
                sb.append(LOG_SEP);
            }
        }

        sb.append(LOG_PARAM_SUFFIX);

        return sb.toString();
    }

    protected static String getMsg(Object object) {

        if (object == null) {
            return LOG_DEFAULT;
        } else if (object.getClass().isInterface()) {
            return LOG_DEFAULT;
        } else if (object.getClass() == String.class
                || object.getClass() == Integer.class
                || object.getClass() == Long.class
                || object.getClass() == Double.class) {
            return object.toString();
        } else if (object instanceof List<?>) {
            return object.toString();
        } else if (object.getClass().isArray()) {
            return Arrays.asList(object).toString();
        } else if (object instanceof Map) {
            // TODO: xx
            return object.toString();
        } else {
            // TODO: xx
            return object.toString();
        }
    }

    protected final String getMsgOfAnonymous(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder anonymousSB = new StringBuilder();
        anonymousSB.append("[");
        for (Field field : fields) {
            try {
                //过滤掉父类的应用
                if ("this$0".equals(field.getName()) || "$jacocoData".equals(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                Object obj = field.get(object);
                if (obj != null) {
                    anonymousSB.append(field.getName())
                            .append(":")
                            .append(getSimpleMsg(obj))
                            .append(";");
                }
            } catch (Exception e) {
                LOGGER.error("getMsgOfAnonymous error" + field.getName(), e);
            }
        }
        anonymousSB.append("]");
        return anonymousSB.toString();
    }

    protected final String getSimpleMsg(Object object) {
        if (object == null) {
            return LOG_DEFAULT;
        } else if (object.getClass() == String.class
                || object.getClass() == Integer.class
                || object.getClass() == Long.class
                || object.getClass() == Double.class) {
            //基本类型直接输出
            return object.toString();
        } else if (object.getClass().isArray()) {
            return Arrays.asList(object).toString();
        } else {
            //复杂入参不打印
            return LOG_DEFAULT;
        }
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }


    public static String isBlankGetDefault(String str, String defaultValue) {
        if (str == null || str.length() == 0) {
            return defaultValue;
        }
        return str;
    }
}
