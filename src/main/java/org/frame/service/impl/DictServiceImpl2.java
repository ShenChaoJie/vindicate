/*package org.frame.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

*//**
 * Created by sunsummer15 on 2017/8/09.
 *//*
@Service
public class DictServiceImpl2 {
    @Autowired
    DictRepository dictRepository;

    public Long getDictLongValueByName(Object name, String dictType) {
        String valueStr = getDictValueByName(name, dictType);
        return StringUtil.getLong(valueStr);
    }

    public String getDictValueByName(Object name, String dictType) {
        List<Map> dictMapList = getDictList(dictType);
        Map dictMap = getDictMap(dictMapList, "dictName", "dictValue");
        return getDictStr(name.toString(), dictMap);
    }

    public void addDictValueByName(List<Map> list, String dictType, String key, String fieldFix, String... nullStrArray) {
        List<Map> dictMapList = getDictList(dictType);
        Map dictMap = getDictMap(dictMapList, "dictName", "dictValue");
        addDict(list, dictMap, key, fieldFix, nullStrArray);
    }

    public void addDictValueByName(Map map, String dictType, String key, String fieldFix, String... nullStrArray) {
        List<Map> dictMapList = getDictList(dictType);
        Map dictMap = getDictMap(dictMapList, "dictName", "dictValue");
        addDict(map, dictMap, key, fieldFix, nullStrArray);
    }


    public String getDictNameByValue(Object value, String dictType) {
        List<Map> dictMapList = getDictList(dictType);
        Map dictMap = getDictMap(dictMapList, "dictValue", "dictName");
        return getDictStr(value.toString(), dictMap);
    }

    public void addDictNameByValue(List<Map> list, String dictType, String key, String fieldFix, String... nullStrArray) {
        List<Map> dictMapList = getDictList(dictType);
        Map dictMap = getDictMap(dictMapList, "dictValue", "dictName");
        addDict(list, dictMap, key, fieldFix, nullStrArray);
    }

    public void addDictNameByValue(Map map, String dictType, String key, String fieldFix, String... nullStrArray) {
        List<Map> dictMapList = getDictList(dictType);
        Map dictMap = getDictMap(dictMapList, "dictValue", "dictName");
        addDict(map, dictMap, key, fieldFix, nullStrArray);
    }

    public String getDictValueExt(Object value, List dictList, String dictFromFiled, String dictToField) {

        List dictMapList = JsonUtil.transformToList(dictList);
        Map dictMap = getDictMap(dictMapList, dictFromFiled, dictToField);
        return getDictStr(value.toString(), dictMap);
    }

    public Long getDictLongValueExt(Object value, List dictList, String dictFromFiled, String dictToField) {
        String valueStr = getDictValueExt(value, dictList, dictFromFiled, dictToField);
        return StringUtil.getLong(valueStr);
    }


    public void addDictByExtList(List<Map> list, List dictList, String dictFromFiled, String dictToFiled, String key, String fieldFix, String... nullStrArray) {
        List dictMapList = JsonUtil.transformToList(dictList);
        Map dictMap = getDictMap(dictMapList, dictFromFiled, dictToFiled);
        addDict(list, dictMap, key, fieldFix, nullStrArray);
    }


    public void addDictByExtList(Map map, List dictList, String dictFromFiled, String dictToFiled, String key, String fieldFix, String... nullStrArray) {
        List dictMapList = JsonUtil.transformToList(dictList);
        Map dictMap = getDictMap(dictMapList, dictFromFiled, dictToFiled);
        addDict(map, dictMap, key, fieldFix, nullStrArray);
    }


    private void addDict(List<Map> list, Map dictMap, String key, String fieldFix, String... nullStrArray) {
        for (Map map : list) {
            addDict(map, dictMap, key, fieldFix, nullStrArray);
        }
    }

    private void addDict(Map map, Map dictMap, String key, String fieldFix, String... nullStrArray) {

        String value = StringUtil.getMapValue(map, key);
        String res = getDictStr(value, dictMap, nullStrArray);
        map.put(key + fieldFix, res);
    }


    private String getDictStr(String value, Map dictMap, String... nullStrArray) {

        String name = StringUtil.getMapValue(dictMap, value);
        if (StringUtil.isBlank(name) && StringUtil.isNotBlank(nullStrArray)) {
            name = nullStrArray[0];
        }
        return name;
    }


    public List<Map> getDictList(String type) {
        List<Map> dictMapList = JsonUtil.transformToList(dictRepository.findByDictTitleOrderByDictValue(type).orElse(new ArrayList<>()));
        return dictMapList;
    }

    *//**
     * 格式化外部dictList
     *
     * @param dictMapList
     * @param dictFromFiled
     * @param dictToFiled
     * @return
     *//*
    private Map getDictMap(List<Map> dictMapList, String dictFromFiled, String dictToFiled) {
        return StringUtil.getMapFromList(dictMapList, dictFromFiled, dictToFiled);
    }

}
*/