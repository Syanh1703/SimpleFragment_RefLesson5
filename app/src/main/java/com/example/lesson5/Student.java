package com.example.lesson5;

import java.io.Serializable;

public class Student implements Serializable {

    String sName;
    int age;

    public Student(String sName, int age) {
        this.sName = sName;
        this.age = age;
    }
}
