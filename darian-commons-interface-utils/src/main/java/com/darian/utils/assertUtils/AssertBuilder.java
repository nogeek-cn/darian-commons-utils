package com.darian.utils.assertUtils;


import java.util.ArrayList;
import java.util.List;

/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a>
 * @date 2019/12/25  21:31
 */
public final class AssertBuilder {
    private AssertBuilderItemEnum assertBuilderItemEnum = AssertBuilderItemEnum.ARRAYS;
    private Boolean firstBoolean;

    private AssertExpression[] assertExpressions;

    private List<AssertExpression> assertExpressionList = new ArrayList<>();

    public static AssertBuilder init(Boolean firstBoolean, AssertExpression... assertExpressions) {
        AssertBuilder assertBuilder = new AssertBuilder();
        assertBuilder.firstBoolean = firstBoolean;
        assertBuilder.assertExpressions = assertExpressions;
        return assertBuilder;
    }

    public static AssertBuilder init(AssertBuilderItemEnum assertBuilderItemEnum,
                                     Boolean firstBoolean, AssertExpression... assertExpressions) {
        AssertBuilder assertBuilder = new AssertBuilder();
        assertBuilder.assertBuilderItemEnum = assertBuilderItemEnum;
        assertBuilder.firstBoolean = firstBoolean;
        if (AssertBuilderItemEnum.ARRAYS.equals(assertBuilderItemEnum)) {
            assertBuilder.assertExpressions = assertExpressions;
        } else if (AssertBuilderItemEnum.LIST.equals(assertBuilderItemEnum)) {
            for (AssertExpression assertExpression : assertExpressions) {
                assertBuilder.assertExpressionList.add(assertExpression);
            }
        } else {
            throw new IllegalArgumentException("assertBuilderItemEnum must be Arrays or List");
        }
        return assertBuilder;
    }

    public AssertBuilder add(AssertExpression assertExpression, AssertExpression... assertExpressions) {
        if (AssertBuilderItemEnum.LIST.equals(this.assertBuilderItemEnum)) {
            assertExpressionList.add(assertExpression);
            for (AssertExpression assertExpressionItem : assertExpressions) {
                assertExpressionList.add(assertExpressionItem);
            }
        } else {
            throw new IllegalArgumentException("when you add item the assertBuilderItemEnum must be List");
        }
        return this;
    }

    public Boolean getAssertBoolean() {
        for (AssertExpression expression : assertExpressions) {
            if (AssertRelationEnum.AND.equals(expression.getAssertRelationEnum())) {
                if (firstBoolean == false) {
                    return false;
                }
                firstBoolean = firstBoolean && expression.getExpressionBoolean().get();
            } else if (AssertRelationEnum.OR.equals(expression.getAssertRelationEnum())) {
                if (firstBoolean == true) {
                    return true;
                }
                firstBoolean = firstBoolean || expression.getExpressionBoolean().get();
            } else {
                throw new IllegalArgumentException("assertRelationEnum must be AND or OR");
            }
        }
        return firstBoolean;
    }
}
