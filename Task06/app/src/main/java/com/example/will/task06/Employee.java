package com.example.will.task06;

@SuppressWarnings({"FieldCanBeLocal", "UnnecessaryThis"})
abstract class Employee {
    private String name;
    private int age;
    private boolean gender;
    private String address;
    private double baseSalary;

    abstract double computeYearlyPay();

    double getBaseSalary(){
        return this.baseSalary;
    }

    public Employee(String name, int age, boolean gender, String address, double baseSalary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.baseSalary = baseSalary;
    }
}
