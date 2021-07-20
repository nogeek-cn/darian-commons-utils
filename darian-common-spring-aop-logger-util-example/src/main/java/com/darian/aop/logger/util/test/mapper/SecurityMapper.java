package com.darian.aop.logger.util.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darian.aop.logger.util.annotation.DalLogger;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@DalLogger
public interface SecurityMapper extends BaseMapper<UserDO> {
}
