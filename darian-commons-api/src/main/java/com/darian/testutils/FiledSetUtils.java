
package com.darian.testutils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FiledSetUtils {

    public static void setPrivateFieldValue(Object target,
                                            String fieldName, Object value) {
        try {

            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
            return;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setPrivateStaticFinalFiledValue(Object target,
                                                       String fieldName, Object value) {
        try {

            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            Field modifiers = field.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(target, value);

            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            return;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setPublicStaticFinalFiledValue(Class<?> clazz,
                                                      String fieldName, Object value) {
        try {

            Field field = clazz.getField(fieldName);
            field.setAccessible(true);

            Field modifiers = field.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, value);

            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            return;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invoke(Object target,
                                String methodName, Object... args) {
        try {

            Class<?>[] argsTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argsTypes[i] = args[i].getClass();
            }
            Method method = target.getClass().getDeclaredMethod(methodName, argsTypes);
            method.setAccessible(true);
            return method.invoke(target, args);
        } catch (NoSuchMethodException | InvocationTargetException |
                IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invoke(Object target, String methodName,
                                Class<?>[] argsTypes, Object[] args) {
        try {
            Method method = target.getClass().getDeclaredMethod(methodName, argsTypes);
            method.setAccessible(true);
            return method.invoke(target, args);
        } catch (NoSuchMethodException | InvocationTargetException |
                IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static List<Class> classList = of(Collections.class);

    public void privateConstructorTest() {
        classList.stream().forEach(T -> {
            Object obj = null;
            try {
                Constructor declaredConstructor = T.getDeclaredConstructor();
                obj = declaredConstructor.newInstance();
            } catch (Exception e) {
            }
            // obj must be null;

            try {
                Constructor declaredConstructor = T.getDeclaredConstructor();
                declaredConstructor.setAccessible(true);
                obj = declaredConstructor.newInstance();
            } catch (Exception e) {
            }

            // obj must be not null;
        });

    }

    private static <T> List<T> of(T... objects) {
        List<T> list = new ArrayList<>();
        for (T object : objects) {
            list.add(object);
        }
        return list;
    }
}