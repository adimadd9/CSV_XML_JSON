package org.lab3.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class CreateCommonCsv {
    private static final String CSV_FILE = "src/main/java/org/lab3/resources/birdsCreated2.csv";

    public static void main(String[] args) {
        String[][] data = {
                {"Randunica", "Hirundinidae", "40", "NotDanger"},
                {"Vultur", "Accipitridae", "3", "Danger"},
                {"Rata", "Anatidae", "15", "NotDanger"},
                {"Lebada", "Anatidae", "2", "NotDanger"},
                {"Pinguin", "Spheniscidae", "7", "NotDanger"}
        };

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE), StandardOpenOption.CREATE_NEW);

                CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.builder()
                        .setHeader("Species", "Family", "Count", "Status").build())
        ) {
            for (String[] row : data) {
                printer.printRecord(Arrays.asList(row));
            }

            printer.flush();

            System.out.println("Fișierul CSV a fost creat cu succes: " + CSV_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
