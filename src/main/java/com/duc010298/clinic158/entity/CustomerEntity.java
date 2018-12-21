package com.duc010298.clinic158.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "clinic", catalog = "")
public class CustomerEntity {
    private int id;
    private String customerName;
    private String nameSearch;
    private Integer yob;
    private String address;
    private String addressSearch;
    private Date dayVisit;
    private Date expectedDob;
    private String result;
    private String note;
    private String report;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
    @Column(name = "expectedDOB")
    public Date getExpectedDob() {
        return expectedDob;
    }

    public void setExpectedDob(Date expectedDob) {
        this.expectedDob = expectedDob;
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
        return id == that.id &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(nameSearch, that.nameSearch) &&
                Objects.equals(yob, that.yob) &&
                Objects.equals(address, that.address) &&
                Objects.equals(addressSearch, that.addressSearch) &&
                Objects.equals(dayVisit, that.dayVisit) &&
                Objects.equals(expectedDob, that.expectedDob) &&
                Objects.equals(result, that.result) &&
                Objects.equals(note, that.note) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, nameSearch, yob, address, addressSearch, dayVisit, expectedDob, result, note, report);
    }
}
