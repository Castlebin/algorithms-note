package com.alibaba.tmf3.core;

import java.util.HashMap;
import java.util.Map;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM5:36
 */
public class ExtConfig {

    private static final ExtConfig instance = new ExtConfig();

    public static ExtConfig getInstance() {
        return instance;
    }

    private Map<String, Object> extensionPointMap;

    public Object getExtPoint(String bizCode) {
        return extensionPointMap.get(bizCode);
    }

    public <T> void putExtPoint(String bizCode, T point) {
        extensionPointMap.put(bizCode, point);
    }

    private ExtConfig() {
        this.extensionPointMap = new HashMap<String, Object>();
    }

}
