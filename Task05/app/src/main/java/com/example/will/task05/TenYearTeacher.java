package com.example.will.task05;

class TenYearTeacher extends Teacher {
    TenYearTeacher(String name, Integer age, Boolean gender, Double baseSalary){
        super(name, age, gender, baseSalary);
    }

    Double calculateSalary(){
        return getBaseSalary() * 1.3;
    }
}
