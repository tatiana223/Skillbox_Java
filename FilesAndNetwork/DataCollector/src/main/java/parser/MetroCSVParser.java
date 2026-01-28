package parser;

import model.StationDate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetroCSVParser {
    public List<StationDate> parseCSV(File csvFile) throws IOException {
        List<StationDate> stations = new ArrayList<>();

        try (BufferedReader reader  = new BufferedReader(new FileReader(csvFile))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String name = parts[0].trim();
                    String date = parts[1].trim();
                    stations.add(new StationDate(name, date));
                }

            }

        }
        return stations;
    }
}
