package com.darian.controller;

import com.darian.domain.request.SearchClassesRequest;
import com.darian.domain.request.SearchMethodsRequest;
import com.darian.domain.response.SearchClassesResponse;
import com.darian.domain.response.SearchMethodsResponse;
import com.darian.service.RemoteRuntimeCallApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/12  10:19
 */
@RestController
public class RemoteRuntimeCallController {

    @Resource
    private RemoteRuntimeCallApi remoteRuntimeCallApi;

    @GetMapping("/searchClassesList")
    public SearchClassesResponse searchClassesList(SearchClassesRequest searchClassesRequest) {
        return remoteRuntimeCallApi.searchClassesList(searchClassesRequest);
    }

    @GetMapping("/searchMethodsList")
    public SearchMethodsResponse searchMethodsList(SearchMethodsRequest searchMethodsRequest) {
        return remoteRuntimeCallApi.searchMethodsList(searchMethodsRequest);
    }
}
