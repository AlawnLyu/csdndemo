package com.lyu.csdndemo.common.util.dict;

import com.lyu.csdndemo.sys.entity.Dict;

import java.util.*;

public class DictCache {

    /**
     * 静态数据字典
     */
    private static Map<String, Map<String, Dict>> dictMap = new Hashtable<>();

    /**
     * 获取数据字典
     *
     * @return
     */
    public static Map<String, Map<String, Dict>> getDictMap() {
        return dictMap;
    }

    /**
     * 载入数据字典缓存
     *
     * @param list
     */
    public static void load(List<Dict> list) {
        for (Dict dict : list) {
            Map<String, Dict> map = dictMap.get(dict.getType());
            if (map == null) {
                map = new Hashtable<>();
                dictMap.put(dict.getType(), map);
            }
            map.put(dict.getCode(), dict);
        }
    }

    /**
     * 重新载入数据字典缓存
     *
     * @param list
     */
    public static void reload(List<Dict> list) {
        dictMap.clear();
        load(list);
    }

    /**
     * 获取数据字典集合
     *
     * @param type
     * @return
     */
    public static Collection<Dict> getDicts(String type) {
        Map<String, Dict> map = dictMap.get(type);
        if (map != null) {
            return map.values();
        }
        return null;
    }

    /**
     * 获取数据字典
     *
     * @param type
     * @param code
     * @return
     */
    public static Dict getDict(String type, String code) {
        Map<String, Dict> map = dictMap.get(type);
        if (map != null) {
            return map.get(code);
        }
        return null;
    }

    /**
     * 获取数据字典
     *
     * @param type
     * @param value
     * @return Dict
     */
    public static Dict getDictByTypeAndValue(String type, String value) {
        Map<String, Dict> map = dictMap.get(type);
        if (map != null) {
            for (Dict dict : map.values()) {
                if (value != null && value.equals(dict.getValue())) {
                    return dict;
                } else if (dict.getValue() == null) {
                    return dict;
                }
            }
        }
        return null;
    }

    /**
     * 获取数据字典值
     *
     * @param type
     * @param code
     * @return Dict
     */
    public static String getDictValue(String type, String code) {
        Map<String, Dict> map = dictMap.get(type);
        if (map != null) {
            Dict dict = map.get(code);
            if (dict != null)
                return dict.getValue();
        }
        return null;
    }
}
