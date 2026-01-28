public class Keyboard {
    public final KeyboardType type;
    public final boolean backlit;
    public final double weight;

    public Keyboard(KeyboardType type, boolean backlit, double weight) {
        this.type = type;
        this.backlit = backlit;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public KeyboardType getType() {
        return type;
    }

    public boolean getBacklit() {
        return backlit;
    }





}
