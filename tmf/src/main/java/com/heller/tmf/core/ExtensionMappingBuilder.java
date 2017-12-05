package com.heller.tmf.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

//构建扩展点和业务对应的扩展点实现的关联关系。
public class ExtensionMappingBuilder {

    private static ExtensionMappingBuilder builder = new ExtensionMappingBuilder();

    private ExtensionMappingBuilder() {}

    private Map<Class, Map<String, Class>> extMap = new HashMap<>();

    public void build() {
        Reflections platformReflections = new Reflections("com.heller.tmf.platform");

        //find all the extension point classes
        Set<Class<?>> extPointClasses = platformReflections.getTypesAnnotatedWith(ExtensionPoint.class);

        Reflections pluginsReflections = new Reflections("com.heller.tmf.plugin");

        //find all the business plugins which implements the extension point
        Set<Class<?>> pluginExtPoints = pluginsReflections.getTypesAnnotatedWith(BizCode.class);

        for (Class<?> pointClass : extPointClasses) {
            Map<String, Class> bizCodeExtMap = new HashMap<>();
            for (Class<?> pluginExtPoint : pluginExtPoints) {
                //if plugin is an implementation of current extension point
                if (pointClass.isAssignableFrom(pluginExtPoint)) {
                    BizCode[] annotationsByType = pluginExtPoint.getAnnotationsByType(BizCode.class);
                    if (annotationsByType != null && annotationsByType.length > 0) {
                        BizCode bizCode = annotationsByType[0];

                        //build the biz code and associated extension implement map
                        bizCodeExtMap.put(bizCode.value(), pluginExtPoint);
                    }
                }
            }
            extMap.put(pointClass, bizCodeExtMap);
        }
    }

    public <Ext> Ext getExtPoint(Class<Ext> extClass, String bizCode) {
        try {
            return (Ext)extMap.get(extClass).get(bizCode).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ExtensionMappingBuilder getInstance() {
        return builder;
    }

}
