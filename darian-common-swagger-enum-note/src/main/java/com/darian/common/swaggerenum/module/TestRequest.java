package com.darian.common.swaggerenum.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/18  下午11:59
 */
@ApiModel("测试模型")
@Data
public class TestRequest {

    @ApiModelProperty(value = "性别", notes = "com.darian.common.swaggerenum.module.GenderEnum")
    private String gender;
}
