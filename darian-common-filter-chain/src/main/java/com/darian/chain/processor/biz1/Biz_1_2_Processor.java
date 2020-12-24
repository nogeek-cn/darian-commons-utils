package com.darian.chain.processor.biz1;

import com.darian.chain.frame.TaskContext;
import com.darian.chain.frame.TaskTypeEnums;
import com.darian.chain.frame.TaskTypeInterface;
import com.darian.chain.processor.TaskProcessor;
import org.springframework.stereotype.Service;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/24  23:22
 */
@Service
public class Biz_1_2_Processor implements TaskProcessor {
    @Override
    public TaskTypeInterface getTaskInterface() {
        //
        return TaskTypeEnums.CommonType.COMMON_TYPE_2;
    }

    @Override
    public void processor(TaskContext taskContext) {
        //
        System.out.println("[Biz_1_1_Processor.processor]");
    }
}
