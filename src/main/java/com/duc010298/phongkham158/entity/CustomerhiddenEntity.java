package com.duc010298.phongkham158.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "customerhidden", schema = "clinic", catalog = "")
public class CustomerhiddenEntity {
    private int id;
    private String name;
    private Integer yob;
    private String address;
    private Date dayVisit;
    private String result;
    private String report;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "YOB")
    public Integer getYob() {
        return yob;
    }

    public void setYob(Integer yob) {
        this.yob = yob;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "dayVisit")
    public Date getDayVisit() {
        return dayVisit;
    }

    public void setDayVisit(Date dayVisit) {
        this.dayVisit = dayVisit;
    }

    @Basic
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "report")
    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerhiddenEntity that = (CustomerhiddenEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(yob, that.yob) &&
                Objects.equals(address, that.address) &&
                Objects.equals(dayVisit, that.dayVisit) &&
                Objects.equals(result, that.result) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, yob, address, dayVisit, result, report);
    }
}
