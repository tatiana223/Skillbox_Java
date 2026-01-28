public class TopManager implements Employee {
    private String name;
    private double fixedSalary;
    private Company company;

    public TopManager(String name, double fixedSalary, Company company) {
        this.name = name;
        this.fixedSalary = fixedSalary;
        this.company = company;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPosition() {
        return "Топ-менеджер";
    }

    @Override
    public double getMonthSalary() {
        return fixedSalary + (company.getIncome() > 10000000 ? fixedSalary * 1.5 : 0);
    }
}
