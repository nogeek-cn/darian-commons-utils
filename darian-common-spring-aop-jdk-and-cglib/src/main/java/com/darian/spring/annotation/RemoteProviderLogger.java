package com.darian.spring.annotation;

import java.lang.annotation.*;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  下午21:52
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RemoteProviderLogger {

    /**
     * 是否打印参数
     *
     * @return
     */
    boolean needParams() default true;

    /**
     * 是否打印结果
     *
     * @return
     */
    boolean needResult() default true;
}
