import java.util.*;

public class Company {

    private List<Employee> employees;
    private double companyIncome;

    public Company() {
        this.employees = new ArrayList<>();
    }

    public void hire(Employee employee) {
        employees.add(employee);
        if (employee instanceof Manager) {
            companyIncome += ((Manager) employee).getEarnedCompany();
        }
        System.out.println(employee.getName() + " принят(а) в компанию на должность " + employee.getPosition());
    }

    public void hireAll(Collection<Employee> employes) {
        employees.addAll(employes);
        for (Employee employee : employes) {
            System.out.println(employee.getName() + " принят(а) в компанию на должность " + employee.getPosition());
        }
    }

    public void fire(Employee employee) {
        employees.remove(employee);
        System.out.println(employee.getName() + " уволен с должности " + employee.getPosition());
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public double getIncome() {
        return companyIncome;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        return new ArrayList<>(employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getMonthSalary))
                .limit(count)
                .toList());
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {
        return new ArrayList<>(employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getMonthSalary).reversed())
                .limit(count)
                .toList());
    }
}
