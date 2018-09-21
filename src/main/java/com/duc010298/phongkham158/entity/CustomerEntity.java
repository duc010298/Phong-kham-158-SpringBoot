package com.duc010298.phongkham158.entity;

import java.util.Date;

public class CustomerEntity {

    private int Id;
    private String Name;
    private String NameS;
    private int YOB;
    private String AddressCus;
    private String AddressCusS;
    private Date DayVisit;
    private Date ExpectedDOB;
    private String Result;
    private String Note;
    private String Report;

    public CustomerEntity() {
    }

    public CustomerEntity(int id, String name, String nameS, int YOB, String addressCus, String addressCusS, Date dayVisit, Date expectedDOB, String result, String note, String report) {
        Id = id;
        Name = name;
        NameS = nameS;
        this.YOB = YOB;
        AddressCus = addressCus;
        AddressCusS = addressCusS;
        DayVisit = dayVisit;
        ExpectedDOB = expectedDOB;
        Result = result;
        Note = note;
        Report = report;
    }

    public String getNameS() {
        return NameS;
    }

    public void setNameS(String nameS) {
        NameS = nameS;
    }

    public String getAddressCusS() {
        return AddressCusS;
    }

    public void setAddressCusS(String addressCusS) {
        AddressCusS = addressCusS;
    }

    public CustomerEntity(String name, String nameS, int YOB, String addressCus, String addressCusS, Date dayVisit, Date expectedDOB, String result, String note, String report) {
        Name = name;
        NameS = nameS;
        this.YOB = YOB;
        AddressCus = addressCus;
        AddressCusS = addressCusS;
        DayVisit = dayVisit;
        ExpectedDOB = expectedDOB;
        Result = result;
        Note = note;

        Report = report;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getYOB() {
        return YOB;
    }

    public void setYOB(int YOB) {
        this.YOB = YOB;
    }

    public String getAddressCus() {
        return AddressCus;
    }

    public void setAddressCus(String addressCus) {
        AddressCus = addressCus;
    }

    public Date getDayVisit() {
        return DayVisit;
    }

    public void setDayVisit(Date dayVisit) {
        DayVisit = dayVisit;
    }

    public Date getExpectedDOB() {
        return ExpectedDOB;
    }

    public void setExpectedDOB(Date expectedDOB) {
        ExpectedDOB = expectedDOB;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getReport() {
        return Report;
    }

    public void setReport(String report) {
        Report = report;
    }
}
