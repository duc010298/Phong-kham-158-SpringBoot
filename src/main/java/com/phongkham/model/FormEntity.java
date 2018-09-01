package com.phongkham.model;

public class FormEntity {

    private int ID;
    private String Name;
    private String Content;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setName(String name) {
        Name = name;
    }

    public FormEntity(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public FormEntity(int ID, String name, String content) {
        this.ID = ID;
        Name = name;
        Content = content;
    }

    public FormEntity() {
    }
}
