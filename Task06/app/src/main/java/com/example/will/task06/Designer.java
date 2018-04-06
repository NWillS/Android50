package com.example.will.task06;

@SuppressWarnings("UnnecessaryThis")
class Designer extends Employee {
    private int numberOfProjects;
    double computeYearlyPay(){
        return (getBaseSalary()+ (double)(10000 * this.numberOfProjects));
    }

    public Designer(String name, int age, boolean gender, String address, double baseSalary, int numberOfProjects) {
        super(name, age, gender, address, baseSalary);
        this.numberOfProjects = numberOfProjects;
    }
}
