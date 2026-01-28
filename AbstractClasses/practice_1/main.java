import java.util.*;

public class main {
    public static void main(String[] args) {
        Company company = new Company();

        // Создаем и нанимаем сотрудников
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 180; i++) {
            employees.add(new Operator("Оператор " + (i + 1), 50000));
        }

        for (int i = 0; i < 80; i++) {
            employees.add(new Manager("Менеджер " + (i + 1), 70000));
        }

        for (int i = 0; i < 10; i++) {
            employees.add(new TopManager("Топ-менеджер " + (i + 1), 150000, company));
        }

        company.hireAll(employees);

        // Выводим топ-10 зарплат
        System.out.println("Топ-10 зарплат:");
        printSalaries(company.getTopSalaryStaff(10));

        // Выводим 30 самых низких зарплат
        System.out.println("30 самых низких зарплат:");
        printSalaries(company.getLowestSalaryStaff(30));

        // Увольняем 50% сотрудников
        List<Employee> toFire = new ArrayList<>(company.getEmployees());
        Collections.shuffle(toFire);
        for (int i = 0; i < toFire.size() / 2; i++) {
            company.fire(toFire.get(i));
        }

        // Выводим топ-10 зарплат после увольнений
        System.out.println("Топ-10 зарплат после увольнений:");
        printSalaries(company.getTopSalaryStaff(10));

        // Выводим 30 самых низких зарплат после увольнений
        System.out.println("30 самых низких зарплат после увольнений:");
        printSalaries(company.getLowestSalaryStaff(30));
    }

    private static void printSalaries(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.printf("%.2f руб.\n", employee.getMonthSalary());
        }
        System.out.println();
    }
}
