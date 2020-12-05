package com.darian.utils;

import com.darian.utils.assertUtils.AssertUtils;
import org.junit.Test;

public class AssertUtilsTest {

    @Test
    public void test() {
        AssertUtils.nonNull(1, "error");
        AssertUtils.isNull(null, "error");
        AssertUtils.isNotBlank("s", "error");
        AssertUtils.isBlank("", "error");
        AssertUtils.isBlank(null, "error");

        AssertUtils.isTrue(() -> true, "error");
        AssertUtils.isTrue(true, "error");

        AssertUtils.isFalse(() -> false, "error");
        AssertUtils.isFalse(false, "error");

        AssertUtils.lessThan(3, 4, "error");
        AssertUtils.lessThanOrEquals(3, 3, "error");

        AssertUtils.GreaterThan(4, 3, "error");
        AssertUtils.lessThanOrEquals(4, 4, "error");


        AssertUtils.isEquals(1,1,"error");
        AssertUtils.isEquals(null, null, "error");
        AssertUtils.isEquals("","", "error");

    }
}
