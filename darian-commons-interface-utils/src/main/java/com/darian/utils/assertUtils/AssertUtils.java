package com.darian.utils.assertUtils;



import java.util.Objects;
import java.util.function.Supplier;

/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a>
 * @date 2019/12/24  0:51
 */
public interface AssertUtils {

    static void nonNull(Object object, String errorMsg) {
        isTrue(Objects.nonNull(object), errorMsg);
    }

    static void isNull(Object object, String errorMsg) {
        isTrue(Objects.isNull(object), errorMsg);
    }

    static void isNotBlank(String string, String errorMsg) {
        isTrue(Objects.nonNull(string) || string.length() > 0, errorMsg);
    }

    static void isBlank(String string, String errorMsg) {
        isTrue(Objects.isNull(string) || string.length() == 0, errorMsg);
    }

    static void isTrue(Supplier<Boolean> booleanSupplier, String errorMsg) {
        isTrue(booleanSupplier.get(), errorMsg);
    }

    static void isFalse(Supplier<Boolean> booleanSupplier, String errorMsg) {
        isFalse(booleanSupplier.get(), errorMsg);
    }

    static void isTrue(Boolean expression, String errorMsg) {
        isFalse(!expression, errorMsg);
    }

    static void isFalse(Boolean expression, String errorMsg) {
        if (expression) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    static void lessThan(Integer number, Integer max, String errorMsg) {
        isTrue(number < max, errorMsg);
    }

    static void lessThanOrEquals(Integer number, Integer max, String errorMsg) {
        isFalse(number > max, errorMsg);
    }

    static void GreaterThan(Integer number, Integer min, String errorMsg) {
        isTrue(number > min, errorMsg);
    }

    static void GreaterThanOrEquals(Integer number, Integer min, String errorMsg) {
        isFalse(number < min, errorMsg);
    }

    static void isEquals(Object actual, Object expect, String errorMsg) {
        isTrue(Objects.equals(actual, expect), errorMsg);
    }



}