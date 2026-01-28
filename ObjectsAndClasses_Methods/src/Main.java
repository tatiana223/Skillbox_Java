public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        Basket basket1 = new Basket();
        basket.add("Milk", 40, 4,0.7);
        basket.add("Milky way",56, 3, 0.3);
        basket1.add("Bread", 50, 2,0.5);
        basket.print("Tanya");
//      System.out.println("Общий вес: " + basket.getTotalWeight() + " kg");
        System.out.println("Общая цена: " + basket.getPrice()+ " ru");
        System.out.println("Кол-во: " + basket.getCount());
        System.out.println("Средняя цена: " + basket.getAverageTotalPrice()+ " ru");


        System.out.println("\n");
        basket1.print("Anton");
        System.out.println("Общий вес: " + basket1.getTotalWeight() + " kg");
        //        System.out.println("Общая цена: " + basket1. averageBasketValue() + " ru");
        System.out.println("Кол-во: " + basket1.getCount());
        System.out.println("Общая цена: " + basket1.getPrice()+ " ru");
        System.out.println("Средняя цена: " + basket1.getAverageTotalPrice()+ " ru");

        System.out.println("\nЦена всех корзин: " + Basket.getTotalPrice());
        System.out.println("Общее кол-во: " + Basket.getTotalCount());
        System.out.println("Средняя цена всех корзин: " + Basket.getAverge());
        System.out.println("Кол-во корзин: " + Basket.getCountBasket());
        System.out.println("Средння цена2: " + Basket.getTotalAveragePriceBasket());

//
//        Arithmetic arithmetic = new Arithmetic(3, 5);
//        arithmetic.sum();
//        arithmetic.proz();
//        arithmetic.max();
//        arithmetic.min();

    }
}
