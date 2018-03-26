package com.derpaholic.utilities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVUtilities {

    public static CSVParser getCSV(String file) {
        try {
            Reader read = Files.newBufferedReader(Paths.get(file));
            return new CSVParser(read, CSVFormat.DEFAULT.withIgnoreHeaderCase());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CSVParser getCSV(String file, String... header) {
        try {
            Reader read = Files.newBufferedReader(Paths.get(file));
            return new CSVParser(read, CSVFormat.DEFAULT.withHeader(header).withIgnoreHeaderCase().withTrim());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
