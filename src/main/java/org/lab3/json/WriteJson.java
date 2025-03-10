package org.lab3.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJson {
    private final static String PATH = "src/main/java/org/lab3/resources/birds.json";

    public static void main(String[] args) {
        JSONObject mainObject = new JSONObject();
        JSONArray familiiArray = new JSONArray();

        familiiArray.add(createFamilie("Hirundinidae", "1", new String[][]{
                {"Randunica", "Hirundo rustica", "1758", "migratoare", "Zbor rapid", "Cioc ascuțit", "2000000"},
        }));
        familiiArray.add(createFamilie("Accipitridae", "2", new String[][]{
                {"Vultur", "Aquila chrysaetos", "1758", "prădător", "Vedere ageră", "Gheare puternice", "150000"},
        }));
        familiiArray.add(createFamilie("Anatidae", "3", new String[][]{
                {"Rata", "Anas platyrhynchos", "1758", "acvatică", "Penaj impermeabil", "Cioc lat", "3000000"},
                {"Lebada", "Cygnus olor", "1758", "acvatică", "Gât lung", "Alb imaculat", "500000"}
        }));
        familiiArray.add(createFamilie("Spheniscidae", "4", new String[][]{
                {"Pinguin", "Aptenodytes forsteri", "1844", "polar", "Nu zboară", "Înoată rapid", "250000"},
        }));

        mainObject.put("familii", familiiArray);

        String jsonString = JSONValue.toJSONString(mainObject);

        try (FileWriter file = new FileWriter(PATH)) {
            file.write(jsonString);
            System.out.println("Fișierul JSON a fost creat cu succes și este formatat!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject createFamilie(String numeFamilie, String idFamilie, String[][] pasariInfo) {
        JSONObject familie = new JSONObject();
        familie.put("nume", numeFamilie);
        familie.put("id", idFamilie);

        JSONArray pasariArray = new JSONArray();

        for (String[] pasareInfo : pasariInfo) {
            JSONObject pasare = new JSONObject();
            pasare.put("id", pasareInfo[0]);
            pasare.put("nume", pasareInfo[1]);
            pasare.put("specie", pasareInfo[2]);
            pasare.put("an_descoperire", pasareInfo[3]);
            pasare.put("tip", pasareInfo[4]);

            JSONArray caracteristici = new JSONArray();
            for (int i = 5; i < pasareInfo.length - 1; i++) {
                caracteristici.add(pasareInfo[i]);
            }

            pasare.put("caracteristici", caracteristici);
            pasare.put("populatie_estimata", pasareInfo[pasareInfo.length - 1]);

            pasariArray.add(pasare);
        }

        familie.put("pasari", pasariArray);
        return familie;
    }
}
