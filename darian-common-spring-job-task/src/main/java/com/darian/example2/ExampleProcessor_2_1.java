package com.darian.example2;

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
public class ExampleProcessor_2_1 extends AbstractJobTaskProcessor<ExampleJobTaskDO_2> {

    @Override
    public String jobTaskProcessorName() {
        return "Example2Processor1";
    }

    @Override
    public JobTaskResult doProcessor(ExampleJobTaskDO_2 taskDO) {
        return JobTaskResult.success();
    }

    @Override
    protected JobTaskProcessorLeverAndOrderSerial getLeverAndOrder() {
        return Example2JobTaskLeverOrderEnum_2.LEVER_1;
    }

    @Override
    protected JobTaskProcessorGroupSerial getGroup() {
        return Example2JobTaskGroupEnum_2.GROUP_1;
    }
}
