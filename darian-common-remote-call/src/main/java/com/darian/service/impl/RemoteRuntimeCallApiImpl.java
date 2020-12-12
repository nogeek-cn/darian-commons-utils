package com.darian.service.impl;

import com.darian.domain.request.SearchClassesRequest;
import com.darian.domain.request.SearchMethodsRequest;
import com.darian.domain.response.SearchClassesResponse;
import com.darian.domain.response.SearchMethodsResponse;
import com.darian.service.RemoteRuntimeCallApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/12  10:06
 */
@Service
public class RemoteRuntimeCallApiImpl implements RemoteRuntimeCallApi, BeanClassLoaderAware {

    private static Logger LOGGER = LoggerFactory.getLogger(RemoteRuntimeCallApiImpl.class);

    private ClassLoader classLoader;

    @Override
    public SearchClassesResponse searchClassesList(SearchClassesRequest searchClassesRequest) {
//        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader contextClassLoader =  classLoader;

        // private final Vector<Class<?>> classes = new Vector<>();
        Field classesField = null;
        try {
            classesField = ClassLoader.class.getDeclaredField("classes");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        classesField.setAccessible(true);

        Vector<Class<?>> classesVector = null;
        try {
            classesVector = (Vector<Class<?>>) classesField.get(contextClassLoader);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        SearchClassesResponse searchClassesResponse = new SearchClassesResponse();
        if (classesVector == null) {
            LOGGER.error("classesVector is null;");
            return searchClassesResponse;
        }


        List<Class<?>> classesList = new LinkedList<>(classesVector);

        for (Class<?> aClass : classesList) {
            if (searchClassesResponse.size() > 50) {
                break;
            }

            if (aClass.getName().contains("sun")) {
                continue;
            }

            LOGGER.debug(aClass.getName());

            if (Objects.nonNull(searchClassesRequest)
                    && Objects.nonNull(searchClassesRequest.getName())
                    && searchClassesRequest.getName().length() > 0) {
                if (aClass.getName().toLowerCase().contains(searchClassesRequest.getName().toLowerCase())) {
                    SearchClassesResponse.ClassOneInfo classOneInfo = new SearchClassesResponse.ClassOneInfo();
                    classOneInfo.setClassName(aClass.getName());
                    classOneInfo.setClassSimpleName(aClass.getSimpleName());
                    searchClassesResponse.add(classOneInfo);
                }
            } else {
                SearchClassesResponse.ClassOneInfo classOneInfo = new SearchClassesResponse.ClassOneInfo();
                classOneInfo.setClassName(aClass.getName());
                classOneInfo.setClassSimpleName(aClass.getSimpleName());
                searchClassesResponse.add(classOneInfo);
            }
        }

        return searchClassesResponse;


    }

    @Override
    public SearchMethodsResponse searchMethodsList(SearchMethodsRequest searchMethodsRequest) {
        SearchMethodsResponse searchMethodsResponse = new SearchMethodsResponse();
        if (Objects.isNull(searchMethodsRequest)) {
            return searchMethodsResponse;
        }

        String className = searchMethodsRequest.getClassName();
        if (Objects.isNull(className) || className.length() == 0) {
            return searchMethodsResponse;
        }


        Class<?> aClass = null;
        try {
            aClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return searchMethodsResponse;
        }

        // 这里没有
        Method[] methods = aClass.getMethods();

        for (Method method : methods) {
            String methodName = method.getName();
            String returnSimpleName = method.getReturnType().getSimpleName();

            Class<?>[] parameterTypes = method.getParameterTypes();


            SearchMethodsResponse.OneMethodInfo oneMethodInfo = new SearchMethodsResponse.OneMethodInfo();
            List<SearchMethodsResponse.MethodOneParameter> parameterList = new ArrayList<>();
            for (Class<?> parameterType : parameterTypes) {
                SearchMethodsResponse.MethodOneParameter methodOneParameter = new SearchMethodsResponse.MethodOneParameter();
                methodOneParameter.setParameterTypeName(parameterType.getSimpleName());
                parameterList.add(methodOneParameter);
            }
            oneMethodInfo.setParameterList(parameterList);
            oneMethodInfo.setMethodName(methodName);
            oneMethodInfo.setReturnSimpleName(returnSimpleName);

            searchMethodsResponse.add(oneMethodInfo);
        }
        return searchMethodsResponse;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    // 获取方法的参数名称
    // LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
    //            String[] parameterNames = discoverer.getParameterNames(method);

}
