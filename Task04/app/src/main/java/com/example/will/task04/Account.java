package com.example.will.task04;

import android.util.Log;

class Account {
    private String name = "";
    private Integer age = 0;
    private String gender = "";
    private String language = "";

    Account(){}

    Account(String name, Integer age, String gender, String language) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.language = language;
    }

    void print(){
        String res = this.name;

        res += this.gender.equals("man") ? "君" : "さん";
        res += "は、" + this.language + " が得意な " + this.age + " 歳です。";

        Log.i("System.out/Print", res);
    }
}
