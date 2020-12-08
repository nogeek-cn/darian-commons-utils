package com.darian.jobTask.composite;

import com.darian.jobTask.module.JobTaskDOSerial;
import com.darian.jobTask.module.JobTaskResult;
import com.darian.jobTask.module.JobTaskResultEnum;
import com.darian.jobTask.processor.AbstractJobTaskProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  1:39
 */
public abstract class JobTaskCompositeDecorator<JobTaskItem extends JobTaskDOSerial>
        implements JobTaskComposite<JobTaskItem>, ApplicationContextAware {

    private static Logger LOGGER = LoggerFactory.getLogger(JobTaskCompositeDecorator.class);


    private Map<String, Map<String, Map<String, List<AbstractJobTaskProcessor>>>> taskInterfaceMap = new HashMap<>();


    @Override
    public JobTaskResult doJobTask(JobTaskItem jobTaskItem) {
        String taskKey = jobTaskItem.getJobTaskProcessorName();
        String group = jobTaskItem.getGroupName();

        Map<String, Map<String, List<AbstractJobTaskProcessor>>> integerMapMap = taskInterfaceMap.get(group);

        if (Objects.isNull(integerMapMap)) {
            return JobTaskResult.fail(JobTaskResultEnum.GROUP_NOT_FOUND);
        }

        for (Map.Entry<String, Map<String, List<AbstractJobTaskProcessor>>> integerMapEntry : integerMapMap.entrySet()) {

            Map<String, List<AbstractJobTaskProcessor>> value = integerMapEntry.getValue();

            for (Map.Entry<String, List<AbstractJobTaskProcessor>> stringListEntry : value.entrySet()) {
                String key = stringListEntry.getKey();

                if (key.equals(taskKey)) {
                    List<AbstractJobTaskProcessor> value1 = stringListEntry.getValue();

                    if (value1.size() > 1) {
                        // TODO: 必须唯一
                        JobTaskResult.fail(JobTaskResultEnum.GROUP_LEVER_PROCESS_MUST_ONE);
                    }
                    AbstractJobTaskProcessor abstractTaskInterface = value1.get(0);
                    LOGGER.info(String.format("[AbstractJobTaskProcessor][%s][jobTaskItem][%s]", abstractTaskInterface, jobTaskItem));
                    JobTaskResult jobTaskResult = abstractTaskInterface.doProcessor(jobTaskItem);
                    return jobTaskResult;
                }
            }

        }

        LOGGER.warn(String.format("[AbstractJobTaskProcessor][jobTaskItem][%s]", jobTaskItem));

        // TODO: 没有找到
        return JobTaskResult.fail(JobTaskResultEnum.PROCESSOR_NOT_FOUND);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AbstractJobTaskProcessor> beansOfType1 = applicationContext.getBeansOfType(AbstractJobTaskProcessor.class);

        Class<? extends JobTaskCompositeDecorator> compositeClass = this.getClass();
        Class<?> compositeT = GenericTypeResolver.resolveTypeArgument(compositeClass, JobTaskCompositeDecorator.class);

        LOGGER.info(String.format("[%s][T][%s]", compositeClass.getSimpleName(), compositeT.getSimpleName()));

        List<AbstractJobTaskProcessor> values = beansOfType1.values().stream()
                .filter(processor -> {
                    Class<? extends AbstractJobTaskProcessor> processorClass = processor.getClass();
                    Class<?> processorT = GenericTypeResolver.resolveTypeArgument(processorClass, AbstractJobTaskProcessor.class);

                    boolean match = Objects.equals(compositeT, processorT);
                    LOGGER.info(
                            String.format("[%s]<%s>\t[%s]<%s>\t[match:%s]",
                                    compositeClass.getSimpleName(), compositeT.getSimpleName(),
                                    processorClass.getSimpleName(), processorT.getSimpleName(),
                                    match)
                    );
                    return match;
                })
                .collect(Collectors.toList());

        Map<String, Map<String, Map<String, List<AbstractJobTaskProcessor>>>> taskInterfaceMap = values.stream()
                .sorted((o1, o2) -> {
                    int o1Order = o1.getOrder();
                    int o2Order = o2.getOrder();
                    return Integer.compare(o1Order, o2Order);
                })
                .collect(
                        Collectors.groupingBy(AbstractJobTaskProcessor::getGroupName,
                                Collectors.groupingBy(AbstractJobTaskProcessor::getLever,
                                        Collectors.groupingBy(AbstractJobTaskProcessor::jobTaskProcessorName))
                        )
                );

        // 校验只能存在一个组
        for (Map.Entry<String, Map<String, Map<String, List<AbstractJobTaskProcessor>>>> stringMapEntry : taskInterfaceMap.entrySet()) {
            Map<String, Map<String, List<AbstractJobTaskProcessor>>> value = stringMapEntry.getValue();

            for (Map.Entry<String, Map<String, List<AbstractJobTaskProcessor>>> mapEntry : value.entrySet()) {
                Map<String, List<AbstractJobTaskProcessor>> value1 = mapEntry.getValue();

                for (Map.Entry<String, List<AbstractJobTaskProcessor>> stringListEntry : value1.entrySet()) {
                    List<AbstractJobTaskProcessor> value2 = stringListEntry.getValue();
                    if (value2.size() > 1) {

                        throw new BeanCreationException("GROUP_LEVER_PROCESS_MUST_ONE");

                    }
                }
            }
        }

        LOGGER.info(String.format("[%s][taskInterfaceMap][%s]:", compositeClass.getSimpleName(), taskInterfaceMap));

        this.taskInterfaceMap = taskInterfaceMap;
    }
}
