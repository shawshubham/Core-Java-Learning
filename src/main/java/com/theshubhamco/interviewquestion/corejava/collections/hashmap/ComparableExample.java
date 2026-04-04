package com.theshubhamco.interviewquestion.corejava.collections.hashmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableExample {
    public static void main(String[] args) {
        Employee emp1 = new Employee(101, "Alice", 10000);
        Employee emp2 = new Employee(102, "Charlie", 12000);
        Employee emp3 = new Employee(103, "Bob", 11000);

        // negative value because 101 < 102
        System.out.println("emp1.compareTo(emp2): " + emp1.compareTo(emp2));
        // 0 value because 101 == 101
        System.out.println("emp1.compareTo(emp1): " + emp1.compareTo(emp1));
        // positive value because 103 > 102
        System.out.println("emp3.compareTo(emp2): " + emp3.compareTo(emp2));

        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp3);
        employees.add(emp2);

        System.out.println("Before sorting: " + employees);

        Collections.sort(employees);

        System.out.println("After sorting: " + employees);
    }
}