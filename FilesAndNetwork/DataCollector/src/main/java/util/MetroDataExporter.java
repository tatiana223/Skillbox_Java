package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MetroDataExporter {
    private final List<MetroLine> lines;
    private final List<MetroStation> stations;
    private final List<StationDate> dates;
    private final List<FileMetroStation> depths;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public MetroDataExporter(List<MetroLine> lines,
                             List<MetroStation> stations,
                             List<StationDate> dates,
                             List<FileMetroStation> depths) {
        this.lines = lines;
        this.stations = stations;
        this.dates = dates;
        this.depths = depths;
    }

    public void exportToFiles(String outputDir) throws IOException {
        exportStationsByLines(outputDir + "/map.json");
        exportStationsWithDetails(outputDir + "/stations.json");
    }

    private void exportStationsByLines(String filePath) throws IOException {
        Map<String, List<String>> stationsByLine = stations.stream()
                .collect(Collectors.groupingBy(
                        MetroStation::getLineNumber,
                        Collectors.mapping(MetroStation::getName, Collectors.toList())
                ));

        List<List<Map<String, String>>> connections = stations.stream()
                .filter(s -> s instanceof WebMetroStation && ((WebMetroStation) s).hasConnection())
                .map(s -> {
                    List<Map<String, String>> connection = new ArrayList<>();
                    Map<String, String> stationInfo = new HashMap<>();
                    stationInfo.put("line", s.getLineNumber());
                    stationInfo.put("station", s.getName());
                    connection.add(stationInfo);
                    // Здесь можно добавить связанные станции
                    return connection;
                })
                .collect(Collectors.toList());

        List<Map<String, String>> lineInfos = lines.stream()
                .map(line -> {
                    Map<String, String> lineInfo = new HashMap<>();
                    lineInfo.put("number", line.getNumber());
                    lineInfo.put("name", line.getName());
                    lineInfo.put("color", getLineColor(line.getNumber()));
                    return lineInfo;
                })
                .collect(Collectors.toList());

        Map<String, Object> metroMap = new HashMap<>();
        metroMap.put("stations", stationsByLine);
        metroMap.put("lines", lineInfos);

        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(metroMap, writer);
        }
    }

    private void exportStationsWithDetails(String filePath) throws IOException {

        List<StationInfo> stationDetailsList = new ArrayList<>();

        Function<String, String> normalizeName = name -> name.trim()
                .toLowerCase()
                .replace("ё", "е") // приводим ё к е
                .replaceAll("[\\s\\p{Punct}]+", ""); // удаляем пунктуацию и пробелы

        Map<String, String> dateByStation = dates.stream()
                .collect(Collectors.toMap(
                        d -> normalizeName.apply(d.getName()),
                        d -> d.getDate().format(DATE_FORMATTER),
                        (d1, d2) -> d1 // при дубликатах берем первую дату
                ));

        Map<String, Double> depthByStation = depths.stream()
                .collect(Collectors.toMap(
                        d -> normalizeName.apply(d.getName()),
                        FileMetroStation::getDepth,
                        (d1, d2) -> d1 // при дубликатах берем первое значение
                ));

        Map<String, String> lineNameByNumber = lines.stream()
                .collect(Collectors.toMap(MetroLine::getNumber, MetroLine::getName));

        for (MetroStation station : stations) {
            StationInfo details = new StationInfo();
            details.setName(station.getName().trim());
            details.setLine(lineNameByNumber.get(station.getLineNumber().trim()));

            String normalizedStationName = normalizeName.apply(station.getName());

            if (dateByStation.containsKey(normalizedStationName)) {
                details.setDate(dateByStation.get(normalizedStationName));
            }

            if (depthByStation.containsKey(normalizedStationName)) {
                details.setDepth(depthByStation.get(normalizedStationName));
            }

            if (station instanceof WebMetroStation) {
                details.setHasConnection(((WebMetroStation) station).hasConnection());
                System.out.println("station.getLineNumber() = [" + station.getLineNumber() + "]");
                System.out.println("lineNameByNumber.keySet() = " + lineNameByNumber.keySet());

            }

            stationDetailsList.add(details);

        }

        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create();

            Map<String, List<StationInfo>> result = new HashMap<>();
            result.put("stations", stationDetailsList);
            gson.toJson(result, writer);
        }
    }

    private String getLineColor(String lineNumber) {
        switch (lineNumber) {
            case "1": return "red";
            case "2": return "blue";
            case "3": return "green";
            case "4": return "orange";
            case "5": return "violet";
            default: return "black";
        }
    }
}