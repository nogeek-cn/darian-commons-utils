package com.darian.utils;

/***
 *
 *
 * @author <a href="1934849492@qq.com">Darian</a>
 * @date 2019/12/24  0:48
 */
public interface NumberUtils {

    static int objectIntegerToInt(Object objectInteger) {
         return objectInteger == null ? 0 : (int) objectInteger;
    }

}
