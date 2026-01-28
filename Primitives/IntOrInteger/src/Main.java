public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

        System.out.println("Заглавные:");
        for (char i = 'А'; i <= 'Е'; i++) {
            System.out.println(i + " - " + (int) i);
        }

        System.out.println("Ё - " + (int) 'Ё');

        for (char i = 'Ж'; i <= 'Я'; i++){
            System.out.println(i + " - " + (int) i);
        }

        System.out.println("Строчные:");
        for (char i = 'а'; i <= 'е'; i++) {
            System.out.println(i + " - " + (int) i);
        }

        System.out.println("ё - " + (int) 'ё');

        for (char i = 'ж'; i <= 'я'; i++){
            System.out.println(i + " - " + (int) i);
        }


    }
}
