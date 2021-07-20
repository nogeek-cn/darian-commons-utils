
package com.darian.aop.logger.util.module;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2020/12/5  19:25
 */
public interface IResultEnum {

    /**
     * 结果码
     *
     * @return int
     */
    int getResultCode();

    /**
     * 结果信息,展示给用户
     *
     * @return String
     */
    String getResultMsg();

    /**
     * 异常信息,用于内部调试
     *
     * @return String
     */
    String getErrorMsg();
}