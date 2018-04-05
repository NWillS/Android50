package com.example.will.task06;

@SuppressWarnings("UnnecessaryThis")
class Designer extends Employee {
    private final Integer numberOfProjects;
    Double computeYearlyPay(){
        return (getBaseSalary()+ (double)(10000 * this.numberOfProjects));
    }

    Designer(String name, Integer age, Boolean gender, String address, Double baseSalary, Integer numberOfProjects){
        super(name, age, gender, address, baseSalary);
        this.numberOfProjects = numberOfProjects;
    }
}
