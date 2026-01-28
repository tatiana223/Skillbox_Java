public class Dimensions {

    private final double width;
    private final double height;
    private final double length;

    public Dimensions(double width, double height, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }


    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double volume() {
        return width * height * length;
    }
}
