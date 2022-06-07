package com.spacifii.konstruct.explore.service.explore;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * This Class has all static methods for Merging Object attributes
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * This method is used to get Annotated attributes names
     * @param classs
     * @param ann
     * @return String[]
     */
    public static String[] findFields(Class<?> classs, Class<? extends Annotation> ann) {
        Set<Field> set = new HashSet<>();
        Set<String> strings = new HashSet<>();
        Class<?> c = classs;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    set.add(field);
                    strings.add(field.getName());
                }
            }
            c = c.getSuperclass();
        }
        int n = strings.size();
        String arr[] = new String[n];
        int i = 0;
        for (String x : strings)
            arr[i++] = x;

        return arr;
    }


    /**
     * This method is used to Merge DTO to actual Object
     * @param o
     * @param t
     * @param <T>
     * @return T
     */
    public static  <T> T getConverted( Object o, Class<T> t){
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String s = objectMapper.writeValueAsString(o);
            return (T) objectMapper.readValue(s,t);


            //return t1;
        } catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

    /**
     * Copy the not null property values of the given source bean into the target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * <p>This is just a convenience method. For more complex transfer needs,
     * consider using a full BeanWrapper.
     * @param source the source bean
     * @param target the target bean
     * @throws BeansException if the copying failed
     * @see //BeanWrapper
     */
    public static void copyPropertiesNotNull(Object source, Object target, String[] s) throws BeansException {
        copyPropertiesNotNull(source, target, null, s);
    }

    private static void setAccessible(Method method) {
        if (!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            method.setAccessible(true);
        }
    }

    /**
     * Copy the not null property values of the given source bean into the given target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * @param source the source bean
     * @param target the target bean
     * @param editable the class (or interface) to restrict property setting to
     * @param ignoreProperties array of property names to ignore
     * @throws BeansException if the copying failed
     * @see //BeanWrapper
     */
    private static void copyPropertiesNotNull(Object source, Object target, Class<?> editable, String... ignoreProperties)
            throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPropertyDescriptor : targetPds) {
            Method targetWriteMethod = targetPropertyDescriptor.getWriteMethod();
            if (targetWriteMethod != null
                    && (ignoreList == null || !ignoreList.contains(targetPropertyDescriptor.getName()))) {
                PropertyDescriptor sourcePropertyDescriptor =
                        getPropertyDescriptor(source.getClass(), targetPropertyDescriptor.getName());
                if (sourcePropertyDescriptor != null) {
                    Method sourceReadMethod = sourcePropertyDescriptor.getReadMethod();
                    if (sourceReadMethod != null &&
                            ClassUtils.isAssignable(
                                    targetWriteMethod.getParameterTypes()[0], sourceReadMethod.getReturnType())) {
                        try {
                            Method targetReadMethod = targetPropertyDescriptor.getReadMethod();
                            setAccessible(sourceReadMethod);
                            setAccessible(targetWriteMethod);
                            Object sourceValue = sourceReadMethod.invoke(source);

                            if (sourceValue != null && targetReadMethod != null) {
                                setAccessible(targetReadMethod);
                                Object targetValue = targetReadMethod.invoke(target);
                                System.out.println("SOURCE -> " + sourceValue + " TARGET -> " + targetValue );
                                if (targetValue == null) {
                                    targetWriteMethod.invoke(target, sourceValue);
                                } else if(targetValue instanceof Collection<?>) {
                                    ((Collection) targetValue).addAll((Collection) sourceValue);
                                } else if (targetValue instanceof Map<?,?>) {
                                    ((Map) targetValue).putAll((Map) sourceValue);
                                } else {
                                    org.springframework.beans.BeanUtils.copyProperties(sourceValue, targetValue, ignoreProperties);
                                }
                            }
                        }
                        catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPropertyDescriptor.getName() +
                                            "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }


    /**
     * This service method converts Iterator to List
     * @param iter
     * @param <T>
     * @return
     */
    public static <T> List<T> copyIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

    /**
     * This service method convrts Iterable to List
     * @param iter
     * @param <E>
     * @return
     */
    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}
