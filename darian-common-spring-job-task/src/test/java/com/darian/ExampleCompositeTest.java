package com.darian;

import com.darian.example.ExampleJobTaskGroupEnum_1;
import com.darian.jobTask.module.JobTaskResult;
import com.darian.example.ExampleCompositeJob_1;
import com.darian.example.ExampleJobTaskDO_1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  3:44
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleCompositeTest {

    @Resource
    private ExampleCompositeJob_1 exampleComposite;

    @Test
    public void test() {
        ExampleJobTaskDO_1 taskDO = new ExampleJobTaskDO_1();
        taskDO.setGroupName(ExampleJobTaskGroupEnum_1.GROUP_1.getGroupName());
        taskDO.setJobTaskProcessorName("ExampleProcessor1");
        JobTaskResult jobTaskResult = exampleComposite.doJobTask(taskDO);
        System.out.println(jobTaskResult);
    }
}
