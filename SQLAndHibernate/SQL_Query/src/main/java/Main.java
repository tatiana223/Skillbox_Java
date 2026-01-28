import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "1234";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            String sql = "SELECT " +
                    "course_name as name, " +
                    "COUNT(*) AS total_purchases, " +
                    "COUNT(DISTINCT MONTH(subscription_date)) as active_months, " +
                    "COUNT(*) / COUNT(DISTINCT MONTH(subscription_date)) AS avg_purchases " +
                    "FROM PurchaseList " +
                    "WHERE YEAR(subscription_date) = 2018 " +
                    "GROUP BY course_name " +
                    "ORDER BY avg_purchases DESC";

            ResultSet resultSet = statement.executeQuery(sql);
            System.out.printf("%-40s %-15s %-15s %-15s%n", "Курс", "Всего покупок", "Активных месяцев", "Среднее");
            System.out.println("-----------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                int totalPurchases = resultSet.getInt("total_purchases");
                int activeMonths = resultSet.getInt("active_months");
                double avgPurchases = resultSet.getDouble("avg_purchases");
                System.out.printf("%-40s %-15d %-15d %-15.2f%n",
                        courseName, totalPurchases, activeMonths, avgPurchases);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}