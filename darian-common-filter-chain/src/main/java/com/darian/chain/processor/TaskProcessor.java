package com.darian.chain.processor;

import com.darian.chain.frame.TaskContext;
import com.darian.chain.frame.TaskTypeInterface;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2020/12/24  23:22
 */
public interface TaskProcessor {

    TaskTypeInterface getTaskInterface();

    void processor(TaskContext taskContext);
}
