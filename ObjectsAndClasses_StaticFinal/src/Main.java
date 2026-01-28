public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor(3.6, 4, ProcessorManufacturer.INTEL, 0.5);
        RAM ram = new RAM(RAMtype.DDR4, 16, 0.2);
        Storage storage = new Storage(StorageType.SSD, 500, 0.3);
        Display display = new Display(15.6, DisplayType.IPS, 0.8);
        Keyboard keyboard = new Keyboard(KeyboardType.Mechanical, true, 0.5);

        Computer computer1 = new Computer("Dell", "Inscpiron 15", processor, ram, storage, display, keyboard);
        System.out.println(computer1.toString());

    }
}
