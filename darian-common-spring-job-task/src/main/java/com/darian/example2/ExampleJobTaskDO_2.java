package com.darian.example2;

import com.darian.jobTask.module.JobTaskDOSerial;
import lombok.Data;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  1:37
 */
@Data
public class ExampleJobTaskDO_2 implements JobTaskDOSerial {

    public String jobTaskProcessorName;

    private String GroupName;

    @Override
    public String getJobTaskProcessorName() {
        return this.jobTaskProcessorName;
    }

    @Override
    public String getGroupName() {
        return this.GroupName;
    }
}
