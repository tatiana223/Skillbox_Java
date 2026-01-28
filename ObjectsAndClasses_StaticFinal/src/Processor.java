
public class Processor {
    public final double frequency;
    public final int cores;
    public final ProcessorManufacturer manufacturer;
    public final double weight;

    public Processor(double frequency, int cores, ProcessorManufacturer manufacturer, double weight) {
        this.frequency = frequency;
        this.cores = cores;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public ProcessorManufacturer getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    public int getCores() {
        return cores;
    }

    public double getFrequency() {
        return frequency;
    }


}
