package com.example.cse_dep_apps.model;

public class Student {
    private String roll;
    private String name;

    public Student(String roll, String name) {
        this.roll = roll;
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }
}
