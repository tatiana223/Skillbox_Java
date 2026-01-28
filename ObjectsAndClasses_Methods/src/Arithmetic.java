public class Arithmetic {
    private int value1;
    private int value2;

    public Arithmetic(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public void sum() {
        int sum = value1 + value2;
        System.out.println("Сумма чисел: " + sum);
    }

    public void proz() {
        int proz = value1 * value2;
        System.out.println("Произведение чисел: " + proz);
    }

    public void max() {
        int max = 0;
        if (value1 > value2) {
            max = value1;
            System.out.println("Максимальное из двух чисел: " + max);
        } else if (value2 > value1){
            max = value2;
            System.out.println("Максимальное из двух чисел: " + max);
        }

    }

    public void min() {
        int min = 0;
        if (value1 < value2) {
            min = value1;
            System.out.println("Минимальное из двух чисел: " + min);
        } else if (value2 < value1){
            min = value2;
            System.out.println("Минимальное из двух чисел: " + min);
        }
    }
}
