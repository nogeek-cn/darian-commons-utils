package com.darian.common.swaggerenum.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/19  下午21:08
 */
@AllArgsConstructor
@Getter
@ToString
public enum GenderEnum {

    GIRL("GIRL", "女"),

    BOY("BOY", "男"),
    ;

    private String code;

    private String desc;
}
