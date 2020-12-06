package com.darian.example;

import com.darian.jobTask.module.JobTaskProcessorGroupSerial;
import lombok.AllArgsConstructor;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  20:21
 */
@AllArgsConstructor
public enum ExampleJobTaskGroupEnum implements JobTaskProcessorGroupSerial {


    GROUP_1("GROUP_1", "分组1"),
    GROUP_2("GROUP_2", "分组1"),


    ;
    private String groupName;

    private String desc;
    ;

    @Override
    public String getGroupName() {
        return this.groupName;
    }
}
