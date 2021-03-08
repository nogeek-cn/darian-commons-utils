package com.darian.math;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/3/8  下午11:05
 */
public class JavaMath {
    public static void main(String[] args) {
        //9223372036854775807
        //9.223372036854776E18
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE * 1.0);

        //922
        //922.3372036854776
        System.out.println(Long.MAX_VALUE / 10000000000000000L);
        System.out.println(Long.MAX_VALUE * 1.0 / 10000000000000000L);

        double nan = Double.NaN;
        Double null_double = null;
        try {
            System.out.println(1 / null_double);
        }catch (Exception e) {
            e.printStackTrace();
        }
        double x = 922.3372036854776;



    }

}
