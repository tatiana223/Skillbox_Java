public class Basket {

    private int count = 0;
    private static double totalPrice = 0;
    private static int totalCount = 0;
    private String items = "";
    private int limit;
    private double totalWeight = 0.0;
    private int price = 0;
    private static int countBasket = 0;


    public Basket() {

        items = "Список товаров:";
        this.limit = 100000;
        mCountBasket(1);
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int price) {
        this();
        this.items += items;
        this.price = price;
    }

    public void add(String name, int price) {

        add(name, price, count, 0.0);
    }

    public void add(String name, int price, int count, double weight) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (this.price + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
                count + " шт. - " + price + " ru";

        totalWeight = totalWeight + weight * count;
        isPrice(price, count);
        isCount(count);
        addItemsAndPrice(price, count);
    }

    public static void mCountBasket(int count) {
        countBasket += count;
    }

    public static int getCountBasket() {
        return countBasket;
    }



    public void isCount(int count) {
        this.count += count;
    }

    public int getCount() {
        return count;
    }

    public void isPrice(int price, int count) {
        this.price += price * count;
    }

    public double getPrice() {
        return price;
    }

    public double getAverageTotalPrice() {
        return getPrice() / getCount();
    }

    public static void addItemsAndPrice(int price, int count) {
        Basket.totalPrice += price * count;
        Basket.totalCount += count;
    }

    public static double getTotalPrice() {
        return totalPrice;
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static double getAverge() {
        return getTotalPrice() / getTotalCount();
    }

    public static double getTotalAveragePriceBasket() {
        return getTotalPrice() / getCountBasket();
    }




    public double getTotalWeight() {
        return totalWeight;
    }


    public void clear() {
        items = "";
        totalPrice = 0;
        totalWeight = 0.0;
    }


    public boolean contains(String name) {

        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}




