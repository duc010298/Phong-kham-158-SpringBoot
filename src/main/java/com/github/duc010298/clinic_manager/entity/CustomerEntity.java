package com.github.duc010298.clinic_manager.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customer", schema = "public", catalog = "postgres")
public class CustomerEntity {
    private UUID id;
    private String name;
    private String nameSearch;
    private int yearOfBirth;
    private String address;
    private String addressSearch;
    private Date dayVisit;
    private Date expectedDateOfBirth;
    private String result;
    private String note;
    private String report;

    @Id
    @Column(name = "id")
    @GeneratedValue
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
    @Column(name = "name_search")
    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    @Basic
    @Column(name = "year_of_birth")
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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
    @Column(name = "address_search")
    public String getAddressSearch() {
        return addressSearch;
    }

    public void setAddressSearch(String addressSearch) {
        this.addressSearch = addressSearch;
    }

    @Basic
    @Column(name = "day_visit")
    public Date getDayVisit() {
        return dayVisit;
    }

    public void setDayVisit(Date dayVisit) {
        this.dayVisit = dayVisit;
    }

    @Basic
    @Column(name = "expected_date_of_birth")
    public Date getExpectedDateOfBirth() {
        return expectedDateOfBirth;
    }

    public void setExpectedDateOfBirth(Date expectedDateOfBirth) {
        this.expectedDateOfBirth = expectedDateOfBirth;
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
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        CustomerEntity that = (CustomerEntity) o;
        return yearOfBirth == that.yearOfBirth &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(nameSearch, that.nameSearch) &&
                Objects.equals(address, that.address) &&
                Objects.equals(addressSearch, that.addressSearch) &&
                Objects.equals(dayVisit, that.dayVisit) &&
                Objects.equals(expectedDateOfBirth, that.expectedDateOfBirth) &&
                Objects.equals(result, that.result) &&
                Objects.equals(note, that.note) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameSearch, yearOfBirth, address, addressSearch, dayVisit, expectedDateOfBirth, result, note, report);
    }
}
