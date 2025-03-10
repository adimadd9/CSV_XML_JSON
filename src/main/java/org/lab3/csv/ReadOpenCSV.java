package org.lab3.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadOpenCSV {
    private static final String PATH = "src/main/java/org/lab3/resources/birds.csv";

    public static void main(String[] args) {
        try (
                CSVReader reader = new CSVReader(new FileReader(PATH));
        ) {
            String [] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                for(String token : nextLine) {
                    System.out.println(token);
                }
                System.out.println("-------------");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
