package com.theshubhamco.interviewquestion.corejava.collections.hashmap;

public class Employee implements Comparable<Employee> {
    private final int id;
    private final String name;
    private final double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getSalary() { return salary; }

    @Override
    public int compareTo(Employee other) {
        // Natural ordering by id
        // Integer.compare(...) is preferred over subtraction
        // because it is clearer and avoids overflow issues.
        // we can also use: return (this.id < other.id) ? -1 : ((this.id == this.id) ? 0 : 1);
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}