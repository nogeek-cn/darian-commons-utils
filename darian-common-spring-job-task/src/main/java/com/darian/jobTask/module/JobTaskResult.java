package com.darian.jobTask.module;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/6  1:25
 */
@ToString
@Getter
@Setter
public class JobTaskResult<T> {

    /**
     * 结果码
     */
    private int resultCode;

    /**
     * 结果信息,展示给用户
     */
    private String resultMsg;

    private T resultObj;

    public boolean isSuccess() {
        return this.resultCode == JobTaskResultEnum.SUCCESS.getResultCode();
    }


    public static JobTaskResult fail(JobTaskResultEnum jobTaskResultEnum) {
        JobTaskResult jobTaskResult = new JobTaskResult();
        jobTaskResult.resultCode = jobTaskResultEnum.getResultCode();
        jobTaskResult.resultMsg = jobTaskResultEnum.getResultMsg();
        return jobTaskResult;
    }

    public static JobTaskResult success() {
        JobTaskResult jobTaskResult = new JobTaskResult();
        jobTaskResult.resultCode = JobTaskResultEnum.SUCCESS.getResultCode();
        jobTaskResult.resultMsg = JobTaskResultEnum.SUCCESS.getResultMsg();
        return jobTaskResult;

    }
}
