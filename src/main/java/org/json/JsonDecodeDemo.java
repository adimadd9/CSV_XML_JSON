package org.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonDecodeDemo {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";

        try {
            Object obj = parser.parse(s);
            JSONArray array = (JSONArray) obj;

            System.out.println("The 2nd element of array");
            System.out.println(array.get(1));
            System.out.println();

            JSONObject obj2 = (JSONObject) array.get(1);
            System.out.println("Field \"1\"");
            System.out.println(obj2.get("1"));

            JSONObject obj3 = (JSONObject) obj2.get("1");
            System.out.println("Field \"2\"");
            System.out.println(obj3.get("2"));

            JSONObject obj4 = (JSONObject) obj3.get("2");
            System.out.println("Field \"3\"");
            System.out.println(obj4.get("3"));

            JSONObject obj5 = (JSONObject) obj4.get("3");
            System.out.println("Field \"4\"");
            System.out.println(obj5.get("4"));


            JSONArray arrayInternal = (JSONArray) obj5.get("4");
            System.out.println("Field \"4\" value -- json array");
            System.out.println(arrayInternal.get(0));
            System.out.println(arrayInternal.get(1));

            JSONObject obj6 = (JSONObject) arrayInternal.get(1);
            System.out.println("-----");
            System.out.println(obj6.get("6"));


//            s = "{}";
//            obj = parser.parse(s);
//            System.out.println("New str : ");
//            System.out.println(obj);
//
//            s = "[5,]";
//            obj = parser.parse(s);
//            System.out.println("Virgula : ");
//            System.out.println(obj);
//
//            s = "[5,,,2]";
//            obj = parser.parse(s);
//            System.out.println("Virgula 2 : ");
//            System.out.println(obj);

        } catch (ParseException pe) {

            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }
    }
}
