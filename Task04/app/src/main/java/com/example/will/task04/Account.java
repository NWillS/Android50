package com.example.will.task04;

class Account {
    private String name = "";
    private int age = 0;
    private String gender = "";
    private String language = "";

    Account(){}

    Account(String name, int age, String gender, String language) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.language = language;
    }

    String getInfo(){
        String res = this.name;

        res += this.gender.equals("man") ? "君" : "さん";
        res += "は、" + this.language + " が得意な " + this.age + " 歳です。";

        return res;
    }
}
