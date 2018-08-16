package com.phongkham.model;


import java.util.Date;

public class CustomerEntity {

    private int Id;
    private String Name;
    private int YOB;
    private String AddressCus;
    private Date DayVisit;
    private Date ExpectedDOB;
    private String Result;
    private String Note;
    private String Report;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, int YOB, String addressCus, Date dayVisit, Date expectedDOB, String result, String note, String report) {
        Name = name;
        this.YOB = YOB;
        AddressCus = addressCus;
        DayVisit = dayVisit;
        ExpectedDOB = expectedDOB;
        Result = result;
        Note = note;
        Report = report;
    }

    public CustomerEntity(int id, String name, int YOB, String addressCus, Date dayVisit, Date expectedDOB, String result, String note, String report) {
        Id = id;
        Name = name;
        this.YOB = YOB;
        AddressCus = addressCus;
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
