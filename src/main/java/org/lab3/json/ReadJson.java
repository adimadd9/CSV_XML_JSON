package org.lab3.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadJson {
    private final static String PATH = "src/main/java/org/lab3/resources/birds.json";

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(PATH)) {
            JSONObject mainObject = (JSONObject) parser.parse(reader);

            JSONArray familiiArray = (JSONArray) mainObject.get("familii");

            for (Object obj : familiiArray) {
                JSONObject familie = (JSONObject) obj;
                String numeFamilie = (String) familie.get("nume");
                String idFamilie = (String) familie.get("id");

                System.out.println("Familia: " + numeFamilie + " (ID: " + idFamilie + ")");

                JSONArray pasariArray = (JSONArray) familie.get("pasari");

                for (Object objPasare : pasariArray) {
                    JSONObject pasare = (JSONObject) objPasare;
                    String idPasare = (String) pasare.get("id");
                    String numePasare = (String) pasare.get("nume");
                    String speciePasare = (String) pasare.get("specie");
                    long anDescoperire = (long) pasare.get("an_descoperire");
                    String tipPasare = (String) pasare.get("tip");
                    JSONArray caracteristici = (JSONArray) pasare.get("caracteristici");
                    long populatieEstimativa = (long) pasare.get("populatie_estimata");

                    System.out.println("\tPasăre ID: " + idPasare);
                    System.out.println("\tNume: " + numePasare);
                    System.out.println("\tSpecie: " + speciePasare);
                    System.out.println("\tAn descoperire: " + anDescoperire);
                    System.out.println("\tTip: " + tipPasare);
                    System.out.println("\tCaracteristici: " + caracteristici);
                    System.out.println("\tPopulație estimată: " + populatieEstimativa);
                    System.out.println();
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
