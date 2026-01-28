package model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StationDate extends MetroStation{
    private LocalDate date;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public StationDate(String name, String dateString) {
        this.name = name;
        this.date = LocalDate.parse(dateString, DATE_FORMATTER);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return name + " (дата открытия: " + date.format(DATE_FORMATTER) + ")";
    }
}
