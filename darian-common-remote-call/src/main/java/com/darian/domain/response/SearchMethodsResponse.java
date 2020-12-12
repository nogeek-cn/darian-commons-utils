package com.darian.domain.response;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/12  10:42
 */
@Data
public class SearchMethodsResponse extends LinkedList<SearchMethodsResponse.OneMethodInfo> {

    @Data
    public static class OneMethodInfo {

        private String methodName;


        // 返回值
        private String returnSimpleName;

        private List<MethodOneParameter> parameterList;


    }

    @Data
    public static class MethodOneParameter {
        private String parameterTypeName;
    }
}
