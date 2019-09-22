package com.gyl.visit.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Collection;

public class JsonUtil {

    public static String toJson(Object o) {
        if (o == null) {
            return "{}";
        }
        String os;
        if (o instanceof Collection || o.getClass().isArray()) {
            os = JSONArray.toJSONString(o);
        } else {
            os = JSONObject.toJSONString(o);
        }
        return os;
    }

    public static JSONObject toJsonObject(Object o) {
        return toJsonObject(toJson(o));
    }

    public static String toJsonDefaultDateFormat(Object o) {
        return toJsonWithDateFormat(o, DateUtil.YYYY_MM_DD_HH_MM_SS);
    }

    public static String toJsonWithDateFormat(Object o, String partten) {
        if (o == null) {
            return "{}";
        }
        String os;
        if (o instanceof Collection || o.getClass().isArray()) {
            os = JSONArray.toJSONStringWithDateFormat(o, partten);
        } else {
            os = JSONObject.toJSONStringWithDateFormat(o, partten);
        }
        return os;

    }

    public static <T> T parseObject(String json, Class<T> c) {
        return JSON.parseObject(json, c);
    }

    public static JSONObject toJsonObject(String json) {
        return JSONObject.parseObject(json);
    }
}
