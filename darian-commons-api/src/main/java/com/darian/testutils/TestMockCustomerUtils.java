package com.darian.testutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alipay.test.acts.runtime.ActsRuntimeContextThreadHold;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.ResolvableType;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 不采用内省，而采用反射的方式是因为，
 * 有的就没有无参构造函数，有的属性没有 #get() , #set() 方法，还有一些 Builder 的方法
 * <p>
 * <p>
 * <p>
 * 2. CGLib之 Enhancer 拦截方法，动态字节码替换
 * 获取 Callback() 方法，进行 mock 时候的拦截
 * <p>
 * 3. fastJson 输出 JSON 格式
 * <dependency>
 * <groupId>com.alibaba</groupId>
 * <artifactId>fastjson</artifactId>
 * <version>1.2.50</version>
 * </dependency>
 * <p>
 * 4. commons-logging.properties 放在 resources
 */
public class TestMockCustomerUtils {

    private static String log_ln = "\n";

    public static List<Class<? extends Annotation>> DEPENDENCY_ANNOTATION_LIST = new ArrayList<>();

    public static String[] DEPENDENCY_ANNOTOTATION_STRING_ARRAY = new String[]{
            "com.alipay.sofa.runtime.api.annotation.SofaReference"};

    static {
        DEPENDENCY_ANNOTATION_LIST.add(Autowired.class);
        DEPENDENCY_ANNOTATION_LIST.add(Resource.class);

        for (String annotationString : DEPENDENCY_ANNOTOTATION_STRING_ARRAY) {
            try {
                Class<? extends Annotation> clazz = (Class<? extends Annotation>)
                        ClassUtils.forName(annotationString, TestMockCustomerUtils.class.getClassLoader());
                DEPENDENCY_ANNOTATION_LIST.add(clazz);
            } catch (ClassNotFoundException e) {

            }
        }

    }

    private static Object mockFieldValue(Class<?> fieldType) {
        return Mockito.mock(fieldType);
    }

    public static void setAllDependency() {
        Object testedObj = ActsRuntimeContextThreadHold.getContext().getTestedObj();
        List<Field> allFieldList = getAllFieldsList(testedObj.getClass());
        allFieldList = findDependencyFieldList(allFieldList);

        for (Field field : allFieldList) {
            Object mockFieldValue = mockFieldValue(field.getType());
            field.setAccessible(true);
            try {
                field.set(testedObj, mockFieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Field> findDependencyFieldList(List<Field> allFieldList) {
        for (int i = 0; i < allFieldList.size(); i++) {
            Field field = allFieldList.get(i);

            Boolean isDependency = false;
            for (Class<? extends Annotation> dependencyAnnotation : DEPENDENCY_ANNOTATION_LIST) {
                // 依赖注入的注解，匹配到一个，就代表通过
                isDependency = isDependency | field.isAnnotationPresent(dependencyAnnotation);
            }

            if (isDependency) {
                continue;
            }

            allFieldList.remove(i);
            i--;
        }

        return allFieldList;
    }


    /**
     * @param target
     * @param clazz
     * @param methodResultMapper 方法名 -> 返回值
     * @param <T>
     * @return
     */
    public static <T> T mockComponentMethodsResult(T target, Class<T> clazz, Map<String, Object> methodResultMapper) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result;
                if ((result = methodResultMapper.get(method.getName())) != null) {
                    return result;
                }

                return methodProxy.invoke(target, objects);
            }
        });
        return (T) enhancer.create();
    }

    public static <T> T mockComponentMethodResult(T target, Class<T> clazz, String methodName, Object result) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals(methodName)) {
                    return result;
                }
                return methodProxy.invoke(target, objects);
            }
        });
        return (T) enhancer.create();
    }

    public static <T> T mock(Class<T> classT, Log logger, String... notMockFieldNames) {
        return mock(classT, logger, new HashMap<Class<?>, Integer>(), notMockFieldNames);
    }

    public static <T> T mock(Class<T> classT, Log logger, Map<Class<?>, Integer> classCountMap, String... notMockFieldNames) {
        T request = null;
        if (classCountMap == null) {
            classCountMap = new HashMap<>();
        }
        if (List.class.isAssignableFrom(classT) || Collection.class.isAssignableFrom(classT)) {
            return (T) Collections.emptyList();
        }
        if (Map.class.isAssignableFrom(classT)) {
            return (T) Collections.emptyMap();
        }
        if (Set.class.isAssignableFrom(classT)) {
            return (T) Collections.emptySet();
        }

        Integer integer = classCountMap.getOrDefault(classT, 0);
        if (integer > 10) {
            return null;
        }
        integer++;
        classCountMap.put(classT, integer);

        Object defaultValue = mockBaseTypeValue(classT);
        if (defaultValue != null) {
            request = (T) defaultValue;
            loggerError(logger, " mock : " + classT + log_ln + toFormatJSONString(request));
            return request;
        }

        try {

            Constructor<T> constructor = classT.getDeclaredConstructor();
            constructor.setAccessible(true);
            request = (T) constructor.newInstance();
            Field[] fields = getAllFields(classT);
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();

                boolean present = Stream.of(notMockFieldNames)
                        .filter(notMockFieldName -> fieldName.equalsIgnoreCase(notMockFieldName))
                        .findAny().isPresent();

                if (present) {
                    loggerError(logger, "field [" + fieldName + "] while be filter, not set , is null");
                    continue;
                }
                // 过滤掉 static final 值的注入
                if (java.lang.reflect.Modifier.isFinal(field.getModifiers()) && java.lang.reflect.Modifier.isStatic(
                        field.getModifiers())) {
                    loggerError(logger, "field [" + fieldName + "] is static and final, not set , is null");
                    continue;
                }

                try {
                    Object fieldValue = getDefaultValue(field, field.getType(), classCountMap);
                    if (fieldValue == null) {
                        loggerError(logger, "field: [" + fieldName + "]" + " fieldValue is null , continue");
                        continue;
                    }
                    loggerError(logger, "field: [" + fieldName + "]" + " fieldValue: ", toFormatJSONString(fieldValue));
                    field.set(request, fieldValue);
                } catch (Exception e) {
                    loggerError(logger, "初始化 mock 失败, 属性 [" + fieldName + "] 赋值失败！：msg:\n ", e);
                }
            }
            return request;

        } catch (
                Exception e) {
            loggerError(logger, "初始化 mock 失败", e);
        }

        loggerError(logger, "mock request: " + toFormatJSONString(request));
        return request;
    }

    private static Object mockBaseTypeValue(Class<?> type) {
        if (type == int.class) {
            return 1;
        } else if (type == Integer.class) {
            return Integer.valueOf(1);
        } else if (type == long.class) {
            return 1L;
        } else if (type == Long.class) {
            return Long.valueOf(1);
        } else if (type == boolean.class) {
            return true;
        } else if (type == Boolean.class) {
            return Boolean.TRUE;
        } else if (type == String.class) {
            // 为了兼容 json 字符串,
            return "{}";
        } else if (type == char.class) {
            return 'a';
        } else if (type == Character.class) {
            return 'a';
        } else if (type.isEnum()) {
            // 枚举类型的 mock
            return type.getEnumConstants()[0];
        } else if (Date.class == type) {
            return new Date();
        } else {
            return null;
        }
    }

    private static Object getDefaultValue(Field field, Class<?> type, Map<Class<?>, Integer> classCountMap)
            throws ClassNotFoundException {
        Object baseTypeValue = mockBaseTypeValue(type);
        if (baseTypeValue != null) {
            return baseTypeValue;
        } else if (type.isArray()) {
            Class<?> componentType = type.getComponentType();
            Object defaultValue = getDefaultValue(field, componentType, classCountMap);
            if (defaultValue == null) {
                return Array.newInstance(type.getComponentType(), 0);
            } else {
                Object array = Array.newInstance(type.getComponentType(), 1);
                Array.set(array, 0, defaultValue);
                return array;
            }
        } else if (List.class.isAssignableFrom(type) || Collection.class.isAssignableFrom(type)) {
            List list = new ArrayList();
            Class<?> itemtype = Class.forName(ResolvableType.forField(field).getGenerics()[0].getType().getTypeName());
            list.add(getDefaultValue(null, itemtype, classCountMap));
            return list;
        } else if (Map.class.isAssignableFrom(type)) {
            Map map = new HashMap<>();
            Class<?> keytype = Class.forName(ResolvableType.forField(field).getGenerics()[0].getType().getTypeName());
            Class<?> valuetype = Class.forName(ResolvableType.forField(field).getGenerics()[1].getType().getTypeName());
            map.put(getDefaultValue(null, keytype, classCountMap),
                    getDefaultValue(null, valuetype, classCountMap)
            );
            return map;
        } else if (Set.class.isAssignableFrom(type)) {
            Set set = new HashSet<>();
            Class<?> itemtype = Class.forName(ResolvableType.forField(field).getGenerics()[0].getType().getTypeName());
            set.add(getDefaultValue(null, itemtype, classCountMap));
            return set;
        } else {
            Object result = TestMockCustomerUtils.mock(type, null, classCountMap);
            return result;
        }
    }

    private static void loggerError(Log logger, String msg, Exception e) {
        if (logger != null) {
            logger.info(log_ln + msg, e);
        }
    }

    private static void loggerError(Log logger, String msg, Object... objects) {
        if (logger != null) {
            msg = log_ln + msg;
            for (Object object : objects) {
                if (object != null) {
                    msg = "-" + log_ln + object;
                }
            }
            logger.info(msg);
        }
    }

    public static Field[] getAllFields(Class<?> cls) {
        List<Field> allFieldsList = getAllFieldsList(cls);
        return (Field[]) allFieldsList.toArray(new Field[allFieldsList.size()]);
    }

    public static List<Field> getAllFieldsList(Class<?> cls) {
        List<Field> allFields = new ArrayList();
        for (Class currentClass = cls; currentClass != null; currentClass = currentClass.getSuperclass()) {
            Field[] declaredFields = currentClass.getDeclaredFields();
            Collections.addAll(allFields, declaredFields);
        }
        return allFields;
    }

    public static void main(String[] args) {

        //listString
        Log logger = LogFactory.getLog(TestMockCustomerUtils.class);
        AA aa = mock(AA.class, logger);
        System.out.println(toFormatJSONString(aa));

        BB bb = mock(BB.class, logger);
        System.out.println(toFormatJSONString(bb));

        List list = mock(List.class, logger);
        System.out.println(list);
    }

    private static String toFormatJSONString(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }
}

class AA {
    public String[] arraysString;
    public List<String> listString;
    public List<Integer> integerList;
    public Map<String, String> map;
    public Collection<String> collection;
    public AbstractList<String> abstractList;
    public ArrayList<String> arrayList;
    public AA aa;
    public String string;
    public BB bb;

    private AA() {

    }

}

class BB {
    public String string;
}