public class Operator implements Employee{

    private String name;
    private double fixedSalary;

    public Operator(String name, double fixedSalary) {
        this.name = name;
        this.fixedSalary = fixedSalary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPosition() {
        return "Оператор";
    }

    @Override
    public double getMonthSalary() {
        return fixedSalary;
    }
}
