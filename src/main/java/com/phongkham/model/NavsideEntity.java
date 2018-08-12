package com.phongkham.model;

public class NavsideEntity {

    private int ID;
    private String Name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public NavsideEntity(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public NavsideEntity() {
    }
}
