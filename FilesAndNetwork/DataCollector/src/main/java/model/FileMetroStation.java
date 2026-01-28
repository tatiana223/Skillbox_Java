package model;

public class FileMetroStation extends MetroStation {
    private double depth;

    public FileMetroStation(String name, double depth) {
        this.name = name;
        this.lineNumber = lineNumber;
        this.depth = depth;
    }

    public double getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return String.format("Станция: %s (Линия %s, глубина: %.1f м)",
                name, lineNumber, depth);
    }
}