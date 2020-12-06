package com.darian.jobTask.processor;

import com.darian.jobTask.module.JobTaskDOSerial;
import com.darian.jobTask.module.JobTaskResult;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  1:10
 */
public interface JobTaskProcessor<JobTaskItem extends JobTaskDOSerial> {

    /**
     * 任务的 key
     *
     * @return
     */
    String jobTaskProcessorName();

    /**
     * 做任务
     *
     * @param jobTaskItem
     * @return
     */
    JobTaskResult doProcessor(JobTaskItem jobTaskItem);

}
