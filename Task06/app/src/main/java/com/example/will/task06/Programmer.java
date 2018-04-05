package com.example.will.task06;

@SuppressWarnings({"UnnecessaryThis", "MagicCharacter"})
class Programmer extends Employee {
    private final Integer numberOfProjects;
    private final Character rank;

    private Integer getBonusByRank(){
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

    Double computeYearlyPay(){
        return getBaseSalary() + (double)(10000 * this.numberOfProjects) + getBonusByRank();
    }

    Programmer(String name, Integer age, Boolean gender, String address, Double baseSalary, Integer numberOfProjects, Character rank){
        super(name, age, gender, address, baseSalary);
        this.numberOfProjects = numberOfProjects;
        this.rank = rank;
    }
}
