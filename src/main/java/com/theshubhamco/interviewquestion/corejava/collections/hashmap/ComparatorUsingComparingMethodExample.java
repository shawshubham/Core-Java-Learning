package com.theshubhamco.interviewquestion.corejava.collections.hashmap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorUsingComparingMethodExample {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(103, "Bob", 75000));
        employees.add(new Employee(101, "Alice", 90000));
        employees.add(new Employee(102, "Charlie", 80000));
        employees.add(new Employee(104, "David", 80000));

        System.out.println("Before sorting: " + employees);

        employees.sort(null); // uses Comparable natural ordering
        System.out.println("After sorting by id using Comparable: " + employees);

        Comparator<Employee> byIdDesc =
                Comparator.comparingInt(Employee::getId).reversed();

        employees.sort(byIdDesc);
        System.out.println("After sorting by id descending: " + employees);

        Comparator<Employee> byName =
                Comparator.comparing(Employee::getName);

        employees.sort(byName);
        System.out.println("After sorting by name: " + employees);

        Comparator<Employee> bySalaryDesc =
                Comparator.comparingDouble(Employee::getSalary).reversed();

        employees.sort(bySalaryDesc);
        System.out.println("After sorting by salary descending: " + employees);

        Comparator<Employee> bySalaryAndName =
                bySalaryDesc.thenComparing(byName);

        employees.sort(bySalaryAndName);
        System.out.println("After sorting by salary descending and then by name: " + employees);
    }
}