package com.darian.jobTask.processor;

import com.darian.jobTask.module.JobTaskDOSerial;
import com.darian.jobTask.module.JobTaskProcessorGroupSerial;
import com.darian.jobTask.module.JobTaskProcessorLeverAndOrderSerial;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  1:20
 */
public abstract class AbstractJobTaskProcessor<JobTaskItem extends JobTaskDOSerial>
        implements JobTaskProcessor<JobTaskItem> {

    /**
     * 获取某个组
     *
     * @return
     */
    public String getGroupName() {
        return getGroup().getGroupName();
    }


    /**
     * 层级
     *
     * @return
     */
    public String getLever() {
        return getLeverAndOrder().getLever();
    }

    /**
     * @return
     */
    public int getOrder() {
        return getLeverAndOrder().getOrder();
    }


    protected abstract JobTaskProcessorLeverAndOrderSerial getLeverAndOrder();

    protected abstract JobTaskProcessorGroupSerial getGroup();
}
