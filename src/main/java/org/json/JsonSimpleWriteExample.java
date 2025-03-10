package org.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonSimpleWriteExample {
    public static void main(String[] args) {

        JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", new Integer(100));

        JSONArray jsonArray= new JSONArray();
        jsonArray.add("msg 1");
        jsonArray.add("msg 2");
        jsonArray.add("msg 3");

        obj.put("messages", jsonArray);

        try (FileWriter file = new FileWriter("json/test2.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

    }

}
