package com.darian.service;

import com.darian.domain.request.SearchClassesRequest;
import com.darian.domain.request.SearchMethodsRequest;
import com.darian.domain.response.SearchClassesResponse;
import com.darian.domain.response.SearchMethodsResponse;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/12  9:54
 */
public interface RemoteRuntimeCallApi {

    SearchClassesResponse searchClassesList(SearchClassesRequest searchClassesRequest);

    SearchMethodsResponse searchMethodsList(SearchMethodsRequest searchMethodsRequest);
}
