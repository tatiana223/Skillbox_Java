package parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    public List<File> findJsonByType(String directoryPath) {
        return findFilesByType(directoryPath, ".json");
    }

    public List<File> findCsvFiles(String directoryPath) {
        return findFilesByType(directoryPath, ".csv");
    }

    public List<File> findFilesByType(String directoryPath, String extension) {
        List<File> foundFiles = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            System.err.println("Директория не существует: " + directoryPath);
            return foundFiles;
        }

        if (!directory.isDirectory()) {
            System.err.println("Указанный путь не является директорией: " + directoryPath);
            return foundFiles;
        }

        searchFilesByType(directory, foundFiles, extension.toLowerCase());
        return foundFiles;
    }

    public void searchFilesByType(File directory, List<File> foundFiles, String extension) {
        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                searchFilesByType(file, foundFiles, extension);
            } else {
                String fileName = file.getName().toLowerCase();
                if (fileName.endsWith(extension)) {  // Проверяем только нужное расширение
                    foundFiles.add(file);
                }
            }
        }
    }

    public void printFoundFiles(List<File> files) {
        int jsonCount = 0;
        int csvCount = 0;

        System.out.println("Найдены файлы: ");

        for (File file : files) {
            if (file.getName().toLowerCase().endsWith(".json")) {
                jsonCount++;
                System.out.println("JSON: " + file.getAbsolutePath());
            } else if (file.getName().toLowerCase().endsWith(".csv")) {
                csvCount++;
                System.out.println("CSV: " + file.getAbsolutePath());
            }
        }

        System.out.println("\nИтого:");
        System.out.println("JSON-файлов: " + jsonCount);
        System.out.println("CSV-файлов: " + csvCount);
    }
}