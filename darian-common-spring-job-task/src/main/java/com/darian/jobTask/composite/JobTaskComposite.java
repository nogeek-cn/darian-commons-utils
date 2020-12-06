package com.darian.jobTask.composite;

import com.darian.jobTask.module.JobTaskDOSerial;
import com.darian.jobTask.module.JobTaskResult;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  1:38
 */
public interface JobTaskComposite<JobTaskItem extends JobTaskDOSerial> {

    JobTaskResult doJobTask(JobTaskItem jobTaskItem);
}
