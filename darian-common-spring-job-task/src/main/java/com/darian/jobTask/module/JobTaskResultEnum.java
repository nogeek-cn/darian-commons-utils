package com.darian.jobTask.module;


import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * 结果枚举类
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2020/12/5  19:15
 */
@Getter
@AllArgsConstructor
public enum JobTaskResultEnum {

    /**
     * 业务成功时
     */
    SUCCESS(200, "成功", ""),

    /**
     * 系统业务异常
     */
    SYSTEM_ERROR(500, "系统异常，请重试", "系统异常，请重试"),

    /**
     * 无效的参数
     */
    ILLEGAL_ARGUMENT_PARAMS(203, "无效的参数", "无效的参数"),

    GROUP_NOT_FOUND(600, "Group没有找到", ""),

    PROCESSOR_NOT_FOUND(800, "处理器没有找到", ""),

    GROUP_LEVER_PROCESS_MUST_ONE(900, "处理器只能有一个", "");

    /**
     * 结果码
     */
    private int resultCode;

    /**
     * 结果信息,展示给用户
     */
    private String resultMsg;

    /**
     * 异常信息,用于内部调试
     */
    private String errorMsg;
}