package com.darian.spring.interceptor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darian.spring.annotation.DalLogger;
import org.apache.ibatis.annotations.Mapper;

/***
 *
 * @author peng_zhan
 */
@Mapper
@DalLogger
public interface SecurityMapper extends BaseMapper<UserDO> {
}
