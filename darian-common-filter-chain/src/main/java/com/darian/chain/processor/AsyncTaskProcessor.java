package com.darian.chain.processor;

import com.darian.chain.frame.TaskContext;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/24  23:38
 */
public interface AsyncTaskProcessor<T> {

    T getResponse(TaskContext taskContext);


    void asyncResponse(T t);
}
