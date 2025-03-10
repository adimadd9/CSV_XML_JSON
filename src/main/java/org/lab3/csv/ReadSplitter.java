package org.lab3.csv;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadSplitter {
    private static final String PATH = "src/main/java/org/lab3/resources/birds.csv";
    private static final String DELIMITER = ",";

    public static void main(String[] args) {
            try (
                    BufferedReader fileReader = new BufferedReader(new FileReader(PATH));
            ) {
                String line = "";

                while ((line = fileReader.readLine()) != null) {
                    String[] tokens = line.split(DELIMITER);

                    for (String token : tokens) {
                        System.out.println(token);
                    }
                    System.out.println("------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
