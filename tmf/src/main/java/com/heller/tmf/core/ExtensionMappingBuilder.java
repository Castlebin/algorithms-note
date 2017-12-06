package com.heller.tmf.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.reflections.Reflections;

//构建扩展点和业务对应的扩展点实现的关联关系。
public class ExtensionMappingBuilder {

    private static ExtensionMappingBuilder builder = new ExtensionMappingBuilder();

    private ExtensionMappingBuilder() {
    }

    private Map<Class, Multimap<String, ExtensionPoint>> extMap = new HashMap<>();

    public void build() {
        Reflections platformReflections = new Reflections("com.heller.tmf.platform");
        //find all the extension point classes
        Set<Class<? extends ExtensionPoint>> extPointClasses = platformReflections.getSubTypesOf(ExtensionPoint.class);

        //find all the business plugins which implements the extension point
        Rebound rebound = new Rebound("com.heller.tmf.plugin");
        Set<Class<? extends ExtensionFacade>> pluginFacades = rebound.getSubClassesOf(ExtensionFacade.class);

        for (Class<? extends ExtensionPoint> pointClass : extPointClasses) {
            Multimap<String, ExtensionPoint> bizCodeExtMap = ArrayListMultimap.create();
            for (Class<? extends ExtensionFacade> facade : pluginFacades) {
                buildPluginMap(pointClass, facade, bizCodeExtMap);
            }
            extMap.put(pointClass, bizCodeExtMap);
        }

    }

    private void buildPluginMap(Class<? extends ExtensionPoint> pointClass,
                                Class<? extends ExtensionFacade> facade,
                                Multimap<String, ExtensionPoint> bizCodeExtMap) {
        BizCode[] annotationsByType = facade.getAnnotationsByType(BizCode.class);
        if (annotationsByType != null && annotationsByType.length > 0) {
            BizCode bizCode = annotationsByType[0];

            //build the biz code and associated extension implement map
            ExtensionPoint plugin = createExtInstance(facade, pointClass);

            bizCodeExtMap.put(bizCode.value(), plugin);
        }

    }

    private ExtensionPoint createExtInstance(Class<? extends ExtensionFacade> facade,
                                             Class<? extends ExtensionPoint> pointClass) {
        Object points = null;
        try {
            points = facade.newInstance();
            Method[] methods = facade.getDeclaredMethods();

            for (Method method : methods) {
                //find the method returns the extensionPoints object in the facade class
                if (pointClass.isAssignableFrom(method.getReturnType())) {
                    try {
                        return (ExtensionPoint)method.invoke(points);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <Ext> Ext getExtPoint(Class<Ext> extClass, String bizCode) {
        //暂时先取第一个扩展实现
        return (Ext)extMap.get(extClass).get(bizCode).iterator().next();
    }

    public <Ext> List<Ext> getExtPoints(Class<Ext> extClass, String bizCode) {

        return null;
        //        return  extMap.get(extClass).get(bizCode);
    }

    public static ExtensionMappingBuilder getInstance() {
        return builder;
    }

}
