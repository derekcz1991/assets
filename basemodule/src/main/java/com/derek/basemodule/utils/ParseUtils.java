package com.derek.basemodule.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;

/**
 * Created by derek on 16/2/21.
 */
public class ParseUtils {

    public static Object parseObject(JSONObject jsonObject, Class clz) throws JSONException {
        return JSON.parseObject(jsonObject.toString(), clz);
    }

    public static ArrayList parseJSONArray(JSONArray jsonArray, Class clz) throws JSONException {
        return (ArrayList) JSON.parseArray(jsonArray.toString(), clz);
    }
}
