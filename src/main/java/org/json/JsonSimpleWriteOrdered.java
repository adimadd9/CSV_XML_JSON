package org.json;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonSimpleWriteOrdered {
    public static void main(String[] args) {
        Map<Object,Object> map = new LinkedHashMap<>();
        map.put("is_vip", new Boolean(true));
        map.put("name", "mkyong.com");
        map.put("age", new Integer(100));
        map.put("balance", new Double(100.21));



        JSONObject obj = new JSONObject();
        System.out.println(obj.toJSONString(map));
    }
}
