package com.darian.example;

import com.darian.jobTask.module.JobTaskProcessorGroupSerial;
import com.darian.jobTask.module.JobTaskProcessorLeverAndOrderSerial;
import com.darian.jobTask.module.JobTaskResult;
import com.darian.jobTask.processor.AbstractJobTaskProcessor;
import org.springframework.stereotype.Service;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  3:41
 */
@Service
public class ExampleProcessor_1_2 extends AbstractJobTaskProcessor<ExampleJobTaskDO_1> {

    @Override
    public String jobTaskProcessorName() {
        return "ExampleProcessor2";
    }

    @Override
    public JobTaskResult doProcessor(ExampleJobTaskDO_1 taskDO) {
        return JobTaskResult.success();
    }

    @Override
    protected JobTaskProcessorLeverAndOrderSerial getLeverAndOrder() {
        return ExampleJobTaskLeverOrderEnum_1.LEVER_1;
    }

    @Override
    protected JobTaskProcessorGroupSerial getGroup() {
        return ExampleJobTaskGroupEnum_1.GROUP_1;
    }
}
