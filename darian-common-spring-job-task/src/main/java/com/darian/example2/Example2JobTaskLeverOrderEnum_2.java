package com.darian.example2;

import com.darian.jobTask.module.JobTaskProcessorLeverAndOrderSerial;
import lombok.AllArgsConstructor;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  20:19
 */

@AllArgsConstructor
public enum Example2JobTaskLeverOrderEnum_2 implements JobTaskProcessorLeverAndOrderSerial {

    LEVER_1("LEVER_1", 1),


    LEVER_2("LEVER_2", 2);

    private String lever;

    private int order;
    ;

    @Override
    public String getLever() {
        return this.lever;
    }

    @Override
    public int getOrder() {
        return this.order;
    }
}
