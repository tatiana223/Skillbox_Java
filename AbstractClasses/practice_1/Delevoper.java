class Delevoper implements Employee{
    private String name;
    private String position;
    private double salary;

    public Delevoper(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "{name='" + name + "', position='" + position + "'}";
    }
}
