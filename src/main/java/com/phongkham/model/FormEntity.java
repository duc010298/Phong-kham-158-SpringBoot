package com.phongkham.model;

public class FormEntity {

    private String Class1;
    private String Class2;

    public String getClass1() {
        return Class1;
    }

    public void setClass1(String class1) {
        Class1 = class1;
    }

    public String getClass2() {
        return Class2;
    }

    public void setClass2(String class2) {
        Class2 = class2;
    }

    public FormEntity(String class1, String class2) {
        Class1 = class1;
        Class2 = class2;
    }

    public FormEntity() {
    }
}
