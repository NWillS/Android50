package com.example.will.task05;

class Teacher {
    private String name;
    private int age;
    private boolean gender;
    private double baseSalary;

    Teacher(){}

    Teacher(String name, int age, boolean gender, double baseSalary){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.baseSalary = baseSalary;
    }

    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public boolean getGender(){
        return this.gender;
    }
    double getBaseSalary(){
        return this.baseSalary;
    }
}