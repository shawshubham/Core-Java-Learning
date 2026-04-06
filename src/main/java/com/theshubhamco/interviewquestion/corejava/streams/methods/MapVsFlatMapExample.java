package com.theshubhamco.interviewquestion.corejava.streams.methods;

import java.util.List;

class Employee {
    private final String name;

    Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // for better readability
    public String toString() {
        return name;
    }
}

class Department {
    private final String name;
    private final List<Employee> employees;

    Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

public class MapVsFlatMapExample {
    public static void main(String[] args) {
        List<Department> departments = List.of(
                new Department("Engineering", List.of(
                        new Employee("Shubham"),
                        new Employee("Amit")
                )),
                new Department("HR", List.of(
                        new Employee("Neha"),
                        new Employee("Priya")
                ))
        );

        // map(): one department -> one employee list
        System.out.println("Using map():");
        departments.stream()
                .map(Department::getEmployees)
                .forEach(System.out::println);

        // flatMap(): one department -> many employees, then flatten
        System.out.println("\nUsing flatMap():");
        departments.stream()
                .flatMap(dept -> dept.getEmployees().stream())
                .forEach(System.out::println);
    }
}