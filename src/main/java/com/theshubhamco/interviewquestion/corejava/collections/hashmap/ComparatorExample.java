package com.theshubhamco.interviewquestion.corejava.collections.hashmap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(103, "Bob", 75000));
        employees.add(new Employee(101, "Alice", 90000));
        employees.add(new Employee(102, "Charlie", 80000));

        System.out.println("Before sorting: " + employees);

        employees.sort(null); // uses Comparable natural ordering
        System.out.println("After sorting by id using Comparable: " + employees);

        Comparator<Employee> byIdDesc =
                (e1, e2) -> Integer.compare(e2.getId(), e1.getId());

        employees.sort(byIdDesc);
        System.out.println("After sorting by id descending: " + employees);

        Comparator<Employee> byName =
                (e1, e2) -> e1.getName().compareTo(e2.getName());

        employees.sort(byName);
        System.out.println("After sorting by name: " + employees);

        Comparator<Employee> bySalaryDesc =
                (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary());

        employees.sort(bySalaryDesc);
        System.out.println("After sorting by salary descending: " + employees);
    }
}

