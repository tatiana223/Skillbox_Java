import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        Elevator elevator = new Elevator(-3, 26);
//        while (true) {
//            System.out.print("Введите номер этажа: ");
//            int floor = new Scanner(System.in).nextInt();
//            elevator.move(floor);
//        }

        Dimensions dimensions = new Dimensions(3.3, 5.4, 1.5);
        InfoCargo cargo = new InfoCargo(dimensions, 5.0, "Pervomay 321", true, "1FD3", false);

        System.out.println("Cargo: " + cargo.getDimensions().volume());
        System.out.println(cargo.getWeight());
        System.out.println(cargo.getDeliveryAddress());
        System.out.println(cargo.getCanBeFlipped());
        System.out.println(cargo.getRegistrationNumber());
        System.out.println(cargo.getFragile());

        InfoCargo modifiedCargo = cargo.withDeliveryAddress("142 Moskovs");
        System.out.println("Заказ с изменненым адресом: ");
        System.out.println(modifiedCargo.getDimensions().volume());
        System.out.println(modifiedCargo.getWeight());
        System.out.println(modifiedCargo.getDeliveryAddress());
        System.out.println(modifiedCargo.getCanBeFlipped());
        System.out.println(modifiedCargo.getRegistrationNumber());
        System.out.println(modifiedCargo.getFragile());

        InfoCargo modifiedDimensions = cargo.modifiedDimensions(new Dimensions(3.2, 10, 1.6));

        System.out.println("Заказ с изменнеными габаритами: ");
        System.out.println(modifiedDimensions.getDimensions().volume());
        System.out.println(modifiedDimensions.getWeight());
        System.out.println(modifiedDimensions.getDeliveryAddress());
        System.out.println(modifiedDimensions.getCanBeFlipped());
        System.out.println(modifiedDimensions.getRegistrationNumber());
        System.out.println(modifiedDimensions.getFragile());
    }
}
