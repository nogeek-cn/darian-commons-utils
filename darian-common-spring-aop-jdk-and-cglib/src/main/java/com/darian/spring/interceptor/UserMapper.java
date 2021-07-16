package com.darian.spring.interceptor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darian.spring.annotation.DalLogger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

/***
 *
 * @author peng_zhan
 */
@Mapper
@DalLogger
public interface UserMapper extends BaseMapper<UserDO> {

    @Select("select * from login_user where id = 1")
    UserDO selectByIdXXXXX(Serializable id);

}
