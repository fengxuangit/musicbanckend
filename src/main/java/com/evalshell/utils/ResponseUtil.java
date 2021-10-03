package com.evalshell.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Map<?, ?> make_response(Object data, String Message, Integer code){
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", data);
        resultMap.put("message", Message);
        resultMap.put("status", code);
        return resultMap;
    }
}
