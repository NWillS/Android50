package com.example.will.task06;

@SuppressWarnings({"FieldCanBeLocal", "UnnecessaryThis"})
abstract class Employee {
    private final String name;
    private final Integer age;
    private final Boolean gender;
    private final String address;
    private final Double baseSalary;

    abstract Double computeYearlyPay();

    Double getBaseSalary(){
        return this.baseSalary;
    }

    Employee(String name, Integer age, Boolean gender, String address, Double baseSalary){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.baseSalary = baseSalary;
    }
}
