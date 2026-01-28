import model.*;
import org.jsoup.nodes.Document;
import parser.FileFinder;
import parser.HtmlParserWeb;
import parser.MetroCSVParser;
import parser.MetroJsonParser;
import util.MetroDataExporter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HtmlParserWeb parser = new HtmlParserWeb();
        FileFinder finder = new FileFinder();

        String dataPath = "C:\\Users\\Tanya\\Учеба\\JAVA\\java_basics\\FilesAndNetwork\\DataCollector\\src\\main\\resources\\data";
        String outputPath = "C:\\Users\\Tanya\\Учеба\\JAVA\\java_basics\\FilesAndNetwork\\DataCollector\\data"; // добавьте эту папку

        try {
            // 1. Парсим данные с сайта
            Document doc = parser.getHtmlDocument();
            List<MetroLine> lines = parser.parseLines(doc);
            List<MetroStation> stations = parser.parseStations(doc);

            // 2. Парсим файлы данных
            List<File> jsonFiles = finder.findJsonByType(dataPath);
            List<File> csvFiles = finder.findCsvFiles(dataPath);

            MetroJsonParser jsonParser = new MetroJsonParser();
            MetroCSVParser csvParser = new MetroCSVParser();

            List<FileMetroStation> depths = new ArrayList<>();
            for (File jsonFile : jsonFiles) {
                depths.addAll(jsonParser.parseStations(jsonFile));
            }

            List<StationDate> dates = new ArrayList<>();
            for (File csvFile : csvFiles) {
                dates.addAll(csvParser.parseCSV(csvFile));
            }


            // 3. Экспортируем данные (НОВЫЙ КОД)
            MetroDataExporter exporter = new MetroDataExporter(lines, stations, dates, depths);
            System.out.println("Создаю станцию: " + stations + ", линия: " + lines);

            exporter.exportToFiles(outputPath);

            System.out.println("Данные успешно сохранены в " + outputPath);

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}