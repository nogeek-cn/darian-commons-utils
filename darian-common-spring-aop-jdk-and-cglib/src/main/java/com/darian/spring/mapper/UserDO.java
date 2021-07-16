package com.darian.spring.mapper;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("login_user")
public class UserDO {

    @TableId
    private Long id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @TableField(value = "gmt_created", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreated;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Integer isDeleted;

    private String username;

    private String actualName;

    private String password;

    private Integer age;

    private String email;

}
