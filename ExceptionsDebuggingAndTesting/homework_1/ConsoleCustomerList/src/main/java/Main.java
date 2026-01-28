import java.util.Scanner;
import org.apache.logging.log4j.*;
public class Main {
    private static final Logger logger = LogManager.getLogger("errors");
    private static final Logger queryLogger = LogManager.getLogger("queries");
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;
    private static final String COMMAND_ADD_ERROR = "Ошибка команды add. Ожидается: add Имя Фамилия Email Телефон\n" +
            "Пример: " + ADD_COMMAND;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            if ("exit".equalsIgnoreCase(command)) {
                break;
            }
            queryLogger.info("Получена команда: " + command);
            String[] tokens = command.split("\\s+", 2);

            switch (tokens[0]) {
                case "add" :
                    if (tokens.length < 2) {
                        System.out.println(COMMAND_ADD_ERROR);
                        logger.error("Попытка добави ть клиента без данных");
                        continue;
                    }
                    try {
                        executor.addCustomer(tokens[1]);
                        System.out.println("Клиент успешно добавлен");
                        queryLogger.info("Добавлен новый клиент: " + tokens[1]);
                    } catch (CustomerStorage.WrongDataFormatException e) {
                        handleException("Ошибка формата данных", e, tokens[1]);
                    } catch (CustomerStorage.WrongPhoneFormatException e) {
                        handleException("Ошибка в номере телефона", e, tokens[1]);
                    } catch (CustomerStorage.WrongEmailFormatException e) {
                        handleException("Ошибка в email", e, tokens[1]);
                    }
                    break;
                case "list":
                    executor.listCustomers();
                    break;
                case "remove":
                    executor.removeCustomer(tokens[1]);
                    break;
                case "count":
                    System.out.println("There are " + executor.getCount() + " customers");
                    break;
                case "help":
                    System.out.println(helpText);
                    break;
                default:
                    System.out.println(COMMAND_ERROR);
                    logger.error("Неверная команда: " + command);
            }
        }
    }
    private static void handleException(String message, RuntimeException e, String input) {
        String errorMessage = message + ": " + e.getMessage() +
                "\nВведенный данные: " + input +
                "\nПример кореектных данных: " + ADD_COMMAND;
        System.out.println(errorMessage);
        logger.error(errorMessage);
    }
}
