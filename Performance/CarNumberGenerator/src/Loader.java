import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.spi.CalendarNameProvider;

public class Loader {

    private static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    private static final int REGION_CODE = 199;
    private static final int THREAD_COUNT = 4;


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i;
            executor.submit(() -> {
                try {
                    generateNumbers(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);


        System.out.println(System.currentTimeMillis() - start);

    }

    private static void generateNumbers(int threadNum) throws Exception {
        try (BufferedOutputStream writer = new BufferedOutputStream(
                new FileOutputStream("res/numbers_" + threadNum + ".txt"))) {
            for (int number = 1 + threadNum; number < 1000; number += THREAD_COUNT) {
                for (char firstLetter : LETTERS) {
                    for (char secondLetter : LETTERS) {
                        for (char thirdLetter : LETTERS) {
                            String carNumber = firstLetter + padNumber(number, 3) + secondLetter + thirdLetter + padNumber(REGION_CODE, 2) + "\n";
                            writer.write(carNumber.getBytes());
                        }
                    }
                }
            }
        }
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        if (numberStr.length() >= numberLength) {
            return numberStr;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = numberStr.length(); i < numberLength; i++) {
            sb.append('0');
        }
        sb.append(numberStr);

        return sb.toString();
    }
}
