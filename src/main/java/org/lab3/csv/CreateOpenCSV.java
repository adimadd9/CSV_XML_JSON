package org.lab3.csv;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CreateOpenCSV {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/lab3/resources/birdsCreated.csv";

        String[][] data = {
                {"Species", "Family", "Count", "Status"},
                {"Randunica", "Hirundinidae", "40", "NotDanger"},
                {"Vultur", "Accipitridae", "3", "Danger"},
                {"Rata", "Anatidae", "15", "NotDanger"},
                {"Lebada", "Anatidae", "2", "NotDanger"},
                {"Pinguin", "Spheniscidae", "7", "NotDanger"}
        };

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER, // Fără ghilimele
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END))
        {
            for (String[] row : data) {
                writer.writeNext(row);
            }

            System.out.println("Fișierul CSV a fost creat cu succes: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
