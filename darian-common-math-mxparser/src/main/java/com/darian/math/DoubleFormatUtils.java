package com.darian.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/3/8  下午11:15
 */
public class DoubleFormatUtils {

    public static void main(String[] args) {
        //保留两位小数第三位如果大于4会进一位（四舍五入）
        double value = 6.2355006;

        print(value);

        print(0.4444);


        System.out.println();
        System.out.println(Long.MAX_VALUE);
        System.out.println((long) Long.MAX_VALUE * 1.0);
    }

    public static void print(double value) {

        System.out.println(fun1(value));
        System.out.println(fun2(value));
        System.out.println(fun3(value));
        System.out.println(fun4(value));

        System.out.println();
    }

    /**
     * 使用精确小数BigDecimal
     */
    public static double fun1(double value) {
        BigDecimal bg = new BigDecimal(value);
        /**
         * 参数：
         newScale - 要返回的 BigDecimal 值的标度。
         roundingMode - 要应用的舍入模式。
         返回：
         一个 BigDecimal，其标度为指定值，其非标度值可以通过此 BigDecimal 的非标度值乘以或除以十的适当次幂来确定。
         */
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    /**
     * DecimalFormat转换最简便
     */
    public static String fun2(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }

    /**
     * String.format打印最简便
     */
    public static String fun3(double value) {
        // 0.44 显示 .44
        return String.format("%.2f", value);
//        return new Formatter().format("%.2f", value).toString();
    }

    /**
     * 使用NumberFormat
     */
    public static String fun4(double value) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        /**
         * setMaximumFractionDigits(int newValue)
         设置数的小数部分所允许的最大位数。
         */
        nf.setMaximumFractionDigits(2);

        return nf.format(value);
    }

}
