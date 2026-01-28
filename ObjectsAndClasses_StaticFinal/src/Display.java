public class Display {
    public final double diagonal;
    public final DisplayType displayType;
    public final double weight;

    public Display(double diagonal, DisplayType displayType, double weight) {
        this.diagonal = diagonal;
        this.displayType = displayType;
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public double getWeight() {
        return weight;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }
}
