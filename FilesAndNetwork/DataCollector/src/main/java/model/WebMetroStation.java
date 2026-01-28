package model;

public class WebMetroStation extends MetroStation {
    private boolean hasConnection;

    public WebMetroStation(String name, String lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    public boolean hasConnection() {
        return hasConnection;
    }

    @Override
    public String toString() {
        return name + " (Линия " + lineNumber + ")" +
                (hasConnection ? " [переход]" : "");
    }
}
