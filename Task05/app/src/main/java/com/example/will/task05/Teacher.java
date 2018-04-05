package com.example.will.task05;

class Teacher {
    private String name;
    private Integer age;
    private Boolean gender;
    private Double baseSalary;

    Teacher(){}

    Teacher(String name, Integer age, Boolean gender, Double baseSalary){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.baseSalary = baseSalary;
    }

    public String getName(){
        return this.name;
    }
    public Integer getAge(){
        return this.age;
    }
    public Boolean getGender(){
        return this.gender;
    }
    Double getBaseSalary(){
        return this.baseSalary;
    }
}