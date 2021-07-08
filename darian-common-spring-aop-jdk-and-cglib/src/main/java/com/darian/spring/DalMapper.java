package com.darian.spring;

import com.darian.spring.annotation.DalLogger;
import org.springframework.stereotype.Repository;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  上午12:53
 */
@Repository
@DalLogger
public interface DalMapper {

    @DalLogger
    int test();
}
