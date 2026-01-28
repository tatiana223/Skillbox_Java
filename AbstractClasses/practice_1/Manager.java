import java.util.Random;

public class Manager implements Employee{
    private String name;
    private double fixedSalary;
    private double earnedCompany;

    public Manager(String name, double fixedSalary) {
        this.name = name;
        this.fixedSalary = fixedSalary;
        this.earnedCompany = new Random().nextInt(140000 - 115000 + 1) + 115000;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPosition() {
        return "Менеджер";
    }

    @Override
    public double getMonthSalary() {
        return fixedSalary + (earnedCompany * 0.05);
    }

    public double getEarnedCompany() {
        return earnedCompany;
    }
}
