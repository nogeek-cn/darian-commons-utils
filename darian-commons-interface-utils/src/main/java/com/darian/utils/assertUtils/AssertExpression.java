package com.darian.utils.assertUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Supplier;

/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a>
 * @date 2019/12/25  22:08
 */
@Data
public class AssertExpression {
    private AssertRelationEnum assertRelationEnum;
    private Supplier<Boolean> expressionBoolean;

    public static AssertExpression init(AssertRelationEnum assertRelationEnum,
                                        Supplier<Boolean> expressionBoolean) {
        AssertExpression assertExpression = new AssertExpression();
        assertExpression.assertRelationEnum = assertRelationEnum;
        assertExpression.expressionBoolean = expressionBoolean;
        return assertExpression;
    }
}
