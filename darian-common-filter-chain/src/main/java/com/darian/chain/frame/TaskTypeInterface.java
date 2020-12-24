package com.darian.chain.frame;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/24  23:18
 */
public interface TaskTypeInterface {

    /**
     * 获取任务名称
     *
     * @return
     */
    default String getTaskName() {
        return this.getClass().getSimpleName();
    }
}
