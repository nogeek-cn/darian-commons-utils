package com.darian.common.swaggerenum.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/19  下午21:11
 */
@ApiModel("测试返回")
@Data
public class TestResponse {

    @ApiModelProperty(value = "性别", notes = "com.darian.common.swaggerenum.module.GenderEnum")
    private String gender;
}
