package com.darian.domain.response;

import lombok.Data;

import java.util.LinkedList;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/12  10:09
 */
@Data
public class SearchClassesResponse extends LinkedList<SearchClassesResponse.ClassOneInfo> {

    @Data
    public static class ClassOneInfo {
        private String className;

        private String classSimpleName;

    }
}
