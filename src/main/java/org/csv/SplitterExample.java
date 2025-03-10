package org.csv;

import java.io.BufferedReader;
import java.io.FileReader;

public class SplitterExample {
    public static void main(String[] args) {
        //Input file which needs to be parsed
        String fileToParse = "example.csv";

        //Delimiter used in CSV file
        final String DELIMITER = ",";

        try (
                //Create the file reader
                BufferedReader fileReader = new BufferedReader(new FileReader(fileToParse));
        ) {
            String line = "";

            //Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                for (String token : tokens) {
                    //Print all tokens
                    System.out.println(token);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
