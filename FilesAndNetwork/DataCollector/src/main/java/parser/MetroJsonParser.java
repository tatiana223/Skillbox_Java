package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.FileMetroStation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MetroJsonParser {
    private static final Gson gson = new Gson();

    public List<FileMetroStation> parseStations(File jsonFile) throws IOException {
        try (Reader reader = new FileReader(jsonFile)) {
            Gson gson = new GsonBuilder().setLenient().create();
            Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();

            List<Map<String, Object>> rawData = gson.fromJson(reader, type);
            List<FileMetroStation> stations = new ArrayList<>();

            for (Map<String, Object> item : rawData) {
                String name = (String) item.get("station_name");
                Double depth = parseDepth(item.get("depth"));

                stations.add(new FileMetroStation(name, depth));
            }
            return stations;
        }
    }

    private double parseDepth(Object depthObj) {
        if (depthObj == null) {
            return 0.0;
        }

        String depthStr = depthObj.toString().trim();
        if (depthStr.isEmpty()) {
            return 0.0;
        }

        String normalized = depthStr.replace(",", ".")
                .replaceAll("[^\\d.-]", "");

        try {
            double result = normalized.isEmpty() ? 0.0 : Double.parseDouble(normalized);
            return result;
        } catch (NumberFormatException e) {
            System.err.println("Ошибка парсинга глубины: '" + depthStr + "'");
            return 0.0;
        }

    }
}