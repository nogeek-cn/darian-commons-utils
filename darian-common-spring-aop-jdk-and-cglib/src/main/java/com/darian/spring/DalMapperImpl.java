package com.darian.spring;

import org.springframework.stereotype.Repository;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a>
 * @date 2021/7/9  上午12:58
 */

@Repository
public class DalMapperImpl implements DalMapper {
    @Override
    public int test() {
        System.out.println("dalmapperImpl test");
        return 1;
    }
}
