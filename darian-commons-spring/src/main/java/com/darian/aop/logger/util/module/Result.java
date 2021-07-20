
package com.darian.aop.logger.util.module;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2020/12/5  19:25
 */
public class Result<T> extends PublicResult {

    private T value;

    public static <T> Result<T> success(T value) {
        Result<T> result = new Result<T>();
        result.setValue(value);
        return result;
    }

    public static <T> Result<T> fail(T value, String errorMessage) {
        Result<T> result = new Result<T>();
        result.setValue(value);
        result.setResultCode(500);
        result.setResultMsg(errorMessage);
        return result;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}