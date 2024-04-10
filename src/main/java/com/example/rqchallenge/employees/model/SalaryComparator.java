package com.example.rqchallenge.employees.model;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return e2.getSalary().compareTo(e1.getSalary());
    }
}
