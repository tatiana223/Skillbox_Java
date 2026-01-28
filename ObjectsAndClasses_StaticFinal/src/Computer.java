public class Computer {

    private Processor processor;
    private RAM ram;
    private Storage storage;
    private Display display;
    private Keyboard keyboard;
    private final String vendor;
    private final String name;

    public Computer(String vendor, String  name, Processor processor, RAM ram, Storage storage, Display display, Keyboard keyboard) {
        this.vendor = vendor;
        this.name = name;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.display = display;
        this.keyboard = keyboard;


    }


    public String toString() {
        return "Computer: " + vendor + " " + name + "\n"
                + "Processor: " + processor.getManufacturer() + " " + processor.getFrequency() + " " + processor.getCores() + processor.getWeight() + "\n"
                + "RAM: " + ram.getType() + " " + ram.getCpacity() + " " + ram.getWeight() + "\n"
                + "Storage: " + storage.getCapacity() + " " + storage.getType()+ " " + storage.getWeight() + "\n"
                + "Display: " + display.getDiagonal() + " " + display.getDisplayType() + " " + display.getWeight() + "\n"
                + "Keyboard: " + keyboard.getType() + " " + keyboard.getBacklit() + " " + keyboard.getWeight() + "\n"
                + "Total Weight: " + totalWeight() + " kg";

    }
    public double totalWeight() {
        return display.getWeight() + processor.getWeight()
                + keyboard.getWeight() + ram.getWeight()
                + storage.getWeight();

    }


    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
