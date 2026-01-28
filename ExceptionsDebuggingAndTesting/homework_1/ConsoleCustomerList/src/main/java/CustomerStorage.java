import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public static class WrongDataFormatException extends RuntimeException {
        public WrongDataFormatException(String message) {
            super(message);
        }
    }

    public static class WrongEmailFormatException extends RuntimeException {
        public WrongEmailFormatException(String message) {
            super(message);
        }
    }

    public static class WrongPhoneFormatException extends RuntimeException {
        public WrongPhoneFormatException(String message) {
            super(message);
        }
    }



    public void addCustomer(String data) {
        final int REQUIRED_COMPONENTS_COUNT = 4;
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");

        if (components.length != REQUIRED_COMPONENTS_COUNT) {
            throw new WrongDataFormatException("Неверное количество данных. Ожидается: Имя Фамилия Email Телефон");
        }

        String email = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.matches(email, components[INDEX_EMAIL])) {
            throw new WrongEmailFormatException("Неверный формат email. Пример: user@gmail.com");
        }

        String phone = "^\\+\\d{11,12}$";
        if (!Pattern.matches(phone, components[INDEX_PHONE])) {
            throw new WrongPhoneFormatException("Неверный формат телефона. Пример +79887775544");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}