package com.example.will.task05;

class FiveYearTeacher extends Teacher {

    FiveYearTeacher(String name, Integer age, Boolean gender, Double baseSalary){
        super(name, age, gender, baseSalary);
    }

    Double calculateSalary(){
        return getBaseSalary() * 1.1;
    }
}
