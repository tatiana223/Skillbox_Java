public class RAM {
    public final RAMtype type;
    public final int cpacity;
    public final double weight;

    public RAM(RAMtype type, int cpacity, double weight) {
        this.type = type;
        this.cpacity = cpacity;
        this.weight = weight;
    }


    public double getWeight() {
        return weight;
    }

    public RAMtype getType() {
        return type;
    }

    public int getCpacity() {
        return cpacity;
    }
}
