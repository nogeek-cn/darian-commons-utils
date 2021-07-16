package com.darian.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darian.spring.annotation.DalLogger;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@DalLogger
public interface SecurityMapper extends BaseMapper<UserDO> {
}
