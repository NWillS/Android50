package com.example.will.task06;

@SuppressWarnings({"UnnecessaryThis", "MagicCharacter"})
class Programmer extends Employee {
    private int numberOfProjects;
    private char rank;

    private int getBonusByRank(){
        switch (this.rank){
            case 'A':
                return 10000;
            case 'B':
                return 7000;
            case 'C':
                return 4000;

            default:
                return 0;
        }
    }

    double computeYearlyPay(){
        return getBaseSalary() + (double)(10000 * this.numberOfProjects) + getBonusByRank();
    }

    public Programmer(String name, int age, boolean gender, String address, double baseSalary, int numberOfProjects, char rank) {
        super(name, age, gender, address, baseSalary);
        this.numberOfProjects = numberOfProjects;
        this.rank = rank;
    }
}
