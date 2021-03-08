package com.darian.math;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Constant;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.concurrent.Callable;

/***
 * https://github.com/mariuszgromada/MathParser.org-mXparser
 * https://gitee.com/Darian1996/MathParser.org-mXparser
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/3/8  下午9:42
 */
public class MathParserExample {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        simpleExpression();
        piExpression();
        customerConstantExpression();
        functionExpression();
        myCustomerExpression();

        try {
            Expression e = new Expression("y+1");
            double calculate = e.calculate();
            System.out.println(e.getExpressionString() + "  :  " + calculate);
            System.out.println("NaN is NaN : " + Double.isNaN(calculate));
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("cost: " +(end - start));

        start = System.currentTimeMillis();
        double x = 300.33;
        for (int i = 0; i < 50; i++) {
            x = x * x;
        }
        System.out.println(x);
        end = System.currentTimeMillis();
        System.out.println("cost: " +(end - start));
    }

    /**
     * 普通表达式
     */
    public static void simpleExpression() {
        Expression e = new Expression("2+(3-5)^2");
        double calculate = e.calculate();
        System.out.println(e.getExpressionString() + "  :  " + calculate);
    }

    /**
     * 圆周率表达式
     */
    public static void piExpression() {
        Expression e = new Expression("2*pi");
        double calculate = e.calculate();
        System.out.println(e.getExpressionString() + "  :  " + calculate);
    }


    /**
     * 自定义变量
     */
    public static void customerConstantExpression() {
        Constant tau = new Constant("tau = 2*pi");
        Expression e = new Expression("3*tau", tau);
        double calculate = e.calculate();
        System.out.println(e.getExpressionString() + "  :  " + calculate);
    }

    /**
     * 数学函数
     */
    public static void functionExpression() {
        Expression e = new Expression("sin(2*pi)");
        double calculate = e.calculate();
        System.out.println(e.getExpressionString() + "  :  " + calculate);
    }


    public static void myCustomerExpression() {
        Argument x = new Argument("x = 5");
        Argument y = new Argument("y = 2*x", x);
        Expression e = new Expression("y+1", y, x);
        double calculate = e.calculate();
        System.out.println(e.getExpressionString() + "  :  " + calculate);

        e = new Expression("y+1", y);
        calculate = e.calculate();
        System.out.println(e.getExpressionString() + "  :  " + calculate);

    }
}
