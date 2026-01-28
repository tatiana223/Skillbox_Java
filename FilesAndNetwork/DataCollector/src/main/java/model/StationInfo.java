package model;

import java.time.LocalDate;

public class StationInfo {
    private String name;
    private String line;
    private String date;
    private Double depth;
    private Boolean hasConnection;

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public Double getDepth() {
        return depth;
    }

    public Boolean getHasConnection() {
        return hasConnection;
    }

    public String getLine() {
        return line;
    }

    public void setHasConnection(Boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLine(String line) {
        this.line = line;
    }

    // Конструктор, геттеры и сеттеры
    @Override
    public String toString() {
        return "Станция: " + name + ", Линия: " + line +
                ", Дата открытия: " + date + ", Глубина: " + depth +
                ", Переход: " + hasConnection;
    }
}