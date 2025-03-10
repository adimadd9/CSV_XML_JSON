package org.lab3.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadScanner {
    private static final String PATH = "src/main/java/org/lab3/resources/birds.csv";

    public static void main(String[] args) throws FileNotFoundException
    {
        try (Scanner scanner = new Scanner(new File(PATH))) {
            scanner.useDelimiter(",");

            while (scanner.hasNext())
            {
                System.out.print(scanner.next() + ", ");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
