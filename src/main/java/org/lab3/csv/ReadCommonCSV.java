package org.lab3.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadCommonCSV {
    private static final String PATH = "src/main/java/org/lab3/resources/birds.csv";

    public static void main(String[] args) throws IOException {
        try (
                Reader reader = new BufferedReader(new FileReader(PATH));
                CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.builder()
                        .setHeader()
                        .setIgnoreHeaderCase(true)
                        .setTrim(true)
                        .build());
        ) {
            for (CSVRecord record : parser) {
                String species = record.get("Species");
                String family = record.get("Family");
                String count = record.get("Count");
                String status = record.get("Status");

                System.out.println("Record no: " + record.getRecordNumber());
                System.out.println("Species: " + species);
                System.out.println("Family: " + family);
                System.out.println("Count: " + count);
                System.out.println("Status: " + status);
                System.out.println("-----------------------\n\n");
            }

        }
    }
}
