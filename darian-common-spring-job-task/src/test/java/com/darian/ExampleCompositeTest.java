package com.darian;

import com.darian.example.ExampleJobTaskGroupEnum;
import com.darian.jobTask.module.JobTaskResult;
import com.darian.example.ExampleCompositeJob;
import com.darian.example.ExampleJobTaskDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ExampleCompositeJob exampleComposite;

    @Test
    public void test() {
        ExampleJobTaskDO taskDO = new ExampleJobTaskDO();
        taskDO.setGroupName(ExampleJobTaskGroupEnum.GROUP_1.getGroupName());
        taskDO.setJobTaskProcessorName("ExampleProcessor1");
        JobTaskResult jobTaskResult = exampleComposite.doJobTask(taskDO);
        System.out.println(jobTaskResult);
    }
}
