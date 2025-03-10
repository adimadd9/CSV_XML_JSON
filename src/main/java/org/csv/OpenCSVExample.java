package org.csv;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class OpenCSVExample {
    public static void main(String[] args) {
        try (
                //Get the CSVReader instance with specifying the delimiter to be used
                CSVReader reader = new CSVReader(new FileReader("example.csv")); //,','); --- constructor deprecated
        ) {
            String [] nextLine;
            //Read one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                for(String token : nextLine)
                {
                    //Print all tokens
                    System.out.println(token);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
