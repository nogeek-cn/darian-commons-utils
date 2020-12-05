package com.darian.utils;

import com.darian.utils.assertUtils.AssertBuilder;
import com.darian.utils.assertUtils.AssertExpression;
import com.darian.utils.assertUtils.AssertRelationEnum;
import org.junit.Assert;
import org.junit.Test;


/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a>
 * @date 2019/12/25  22:05
 */
public class AssertBuilderTest {

    @Test
    public void Test() {
        Boolean assertBoolean = new AssertBuilder().init(true,
                AssertExpression.init(AssertRelationEnum.OR, () -> true))
                .getAssertBoolean();
        Assert.assertTrue(assertBoolean);

        assertBoolean = new AssertBuilder().init(true,
                AssertExpression.init(AssertRelationEnum.AND, () -> true))
                .getAssertBoolean();

        Assert.assertTrue(assertBoolean);

        assertBoolean = new AssertBuilder().init(false,
                AssertExpression.init(AssertRelationEnum.OR, () -> true))
                .getAssertBoolean();
        Assert.assertTrue(assertBoolean);

        assertBoolean = new AssertBuilder().init(false,
                AssertExpression.init(AssertRelationEnum.OR, () -> false))
                .getAssertBoolean();
        Assert.assertFalse(assertBoolean);


        assertBoolean = new AssertBuilder().init(false,
                AssertExpression.init(AssertRelationEnum.AND, () -> true))
                .getAssertBoolean();
        Assert.assertFalse(assertBoolean);

        assertBoolean = new AssertBuilder().init(false,
                AssertExpression.init(AssertRelationEnum.AND, () -> false))
                .getAssertBoolean();
        Assert.assertFalse(assertBoolean);

        assertBoolean = AssertBuilder
                .init(true,
                        AssertExpression.init(AssertRelationEnum.AND, () -> true),
                        AssertExpression.init(AssertRelationEnum.AND, () -> true),
                        AssertExpression.init(AssertRelationEnum.AND, () -> true))
                .getAssertBoolean();
        Assert.assertTrue(assertBoolean);

    }

    @Test
    public void testNull() {
        String str = null;
        Boolean assertBoolean = AssertBuilder
                .init(false,
                        AssertExpression.init(AssertRelationEnum.AND, () -> str.length() == 1)
                )
                .getAssertBoolean();

        Assert.assertFalse(assertBoolean);

        assertBoolean = AssertBuilder
                .init(true,
                        AssertExpression.init(AssertRelationEnum.OR, () -> str.length() == 1)
                )
                .getAssertBoolean();

        Assert.assertTrue(assertBoolean);

    }
}
