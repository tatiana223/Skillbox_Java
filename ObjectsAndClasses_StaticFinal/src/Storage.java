public class Storage {
    public final StorageType type;
    public final int capacity;
    public final double weight;

    public Storage(StorageType type, int capacity, double weight) {
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }

    public StorageType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public int getCapacity() {
        return capacity;
    }
}
