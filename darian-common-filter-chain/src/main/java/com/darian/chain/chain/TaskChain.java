package com.darian.chain.chain;

import com.darian.chain.processor.TaskProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/24  23:45
 */
@Service
public class TaskChain implements ApplicationContextAware {

    private static Map<String, TaskProcessor> beanMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, TaskProcessor> beansOfType = applicationContext.getBeansOfType(TaskProcessor.class);
        for (TaskProcessor taskProcessor : beansOfType.values()) {
            beanMap.put(taskProcessor.getTaskInterface().getTaskName(), taskProcessor);
        }
    }

    public static List<TaskProcessor> getProcessorList(List<String> taskNameList) {
        if (beanMap == null || beanMap.size() == 0) {
            throw new RuntimeException("[beanMap][not][init]");
        }

        return taskNameList.stream()
                .map(key -> beanMap.get(key))
                .collect(Collectors.toList());
    }
}
