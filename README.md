# 主页
- https://gitee.com/Darian1996/darian-commons-utils
- https://github.com/Darian1996/darian-commons-utils


# darian-commons-utils

darian-commons-utils：日常工作的总结


## darian-common-spring-aop-logger-util
## darian-common-spring-aop-logger-util-example


### 运行结果

```java
@EnableAOPLogger(multipleLoggerFile = true, baseMapperAspect = true)
```


```c
2021-07-21 13:07:27.217 [main] ERROR TestAOPApplication - 开始测试
2021-07-21 13:07:27.217 [main] ERROR TestAOPApplication - 正常流量 无 traceId, rpcId, parentAppName, parentHostIp:
2021-07-21 13:07:27.304 [main] INFO  DAL_LOGGER - (-,-,-,-)[(BaseMapper.selectById,Y,81ms)]
2021-07-21 13:07:27.332 [main] INFO  DAL_LOGGER - (-,-,-,-)[(UserMapper.selectByIdXXXXX,Y,28ms)]
2021-07-21 13:07:27.332 [main] INFO  SERVICE_LOGGER - (-,-,-,-)[(TestService.test,Y,110ms)]
2021-07-21 13:07:27.334 [main] INFO  CACHE_LOGGER - (-,-,-,-)[(TestCacheService.test,Y,1ms)]
2021-07-21 13:07:27.337 [main] INFO  REMOTE_CALL_LOGGER - (-,-,-,-)[(TestRemoteCallService.test,Y,2ms)]
2021-07-21 13:07:27.338 [main] INFO  REMOTE_PROVIDER_LOGGER - (-,-,-,-)[(TestRemoteProviderService.test,Y,1ms)]
2021-07-21 13:07:27.338 [main] INFO  CONTROLLER_LOGGER - (-,-,-,-)[(TestController.test,Y,118ms)]
2021-07-21 13:07:27.339 [main] INFO  CONTROLLER_LOGGER - (-,-,-,-)[(TestController.testError,N,0ms)]
2021-07-21 13:07:27.339 [main] ERROR TestAOPApplication - 正常流量，有 traceId, rpcId, parentAppName, parentHostIp:
2021-07-21 13:07:27.369 [main] INFO  DAL_LOGGER - (mockTraceId,mockRpcId,mockParentAppName,mockParentHostIp)[(BaseMapper.selectById,Y,30ms)]
2021-07-21 13:07:27.401 [main] INFO  DAL_LOGGER - (mockTraceId,mockRpcId,mockParentAppName,mockParentHostIp)[(UserMapper.selectByIdXXXXX,Y,32ms)]
2021-07-21 13:07:27.401 [main] INFO  SERVICE_LOGGER - (mockTraceId,mockRpcId,mockParentAppName,mockParentHostIp)[(TestService.test,Y,62ms)]
2021-07-21 13:07:27.401 [main] INFO  CACHE_LOGGER - (mockTraceId,mockRpcId,mockParentAppName,mockParentHostIp)[(TestCacheService.test,Y,0ms)]
2021-07-21 13:07:27.401 [main] INFO  REMOTE_CALL_LOGGER - (mockTraceId,mockRpcId,mockParentAppName,mockParentHostIp)[(TestRemoteCallService.test,Y,0ms)]
2021-07-21 13:07:27.401 [main] INFO  REMOTE_PROVIDER_LOGGER - (mockTraceId,mockRpcId,mockParentAppName,mockParentHostIp)[(TestRemoteProviderService.test,Y,0ms)]
2021-07-21 13:07:27.402 [main] INFO  CONTROLLER_LOGGER - (mockTraceId,mockRpcId,mockParentAppName,mockParentHostIp)[(TestController.test,Y,63ms)]
2021-07-21 13:07:27.402 [main] ERROR TestAOPApplication - 压测流量，有 traceId, rpcId, parentAppName, parentHostIp:
2021-07-21 13:07:27.430 [main] INFO  DAL_SHADOW_LOGGER - (shadowMockTraceId,shadowMockRpcId,shadowMockParentAppName,shadowMockParentHostIp)[(BaseMapper.selectById,Y,28ms)]
2021-07-21 13:07:27.461 [main] INFO  DAL_SHADOW_LOGGER - (shadowMockTraceId,shadowMockRpcId,shadowMockParentAppName,shadowMockParentHostIp)[(UserMapper.selectByIdXXXXX,Y,30ms)]
2021-07-21 13:07:27.461 [main] INFO  SERVICE_SHADOW_LOGGER - (shadowMockTraceId,shadowMockRpcId,shadowMockParentAppName,shadowMockParentHostIp)[(TestService.test,Y,59ms)]
2021-07-21 13:07:27.462 [main] INFO  CACHE_SHADOW_LOGGER - (shadowMockTraceId,shadowMockRpcId,shadowMockParentAppName,shadowMockParentHostIp)[(TestCacheService.test,Y,0ms)]
2021-07-21 13:07:27.463 [main] INFO  REMOTE_CALL_SHADOW_LOGGER - (shadowMockTraceId,shadowMockRpcId,shadowMockParentAppName,shadowMockParentHostIp)[(TestRemoteCallService.test,Y,0ms)]
2021-07-21 13:07:27.463 [main] INFO  REMOTE_PROVIDER_SHADOW_LOGGER - (shadowMockTraceId,shadowMockRpcId,shadowMockParentAppName,shadowMockParentHostIp)[(TestRemoteProviderService.test,Y,0ms)]
2021-07-21 13:07:27.464 [main] INFO  CONTROLLER_SHADOW_LOGGER - (shadowMockTraceId,shadowMockRpcId,shadowMockParentAppName,shadowMockParentHostIp)[(TestController.test,Y,62ms)]
2021-07-21 13:07:27.464 [main] ERROR TestAOPApplication - 测试结束


```


## 依赖的一些组件
### Preconditions
com.google.guave:guava:19.0
