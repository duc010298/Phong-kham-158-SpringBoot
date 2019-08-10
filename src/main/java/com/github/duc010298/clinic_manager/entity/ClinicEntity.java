package com.github.duc010298.clinic_manager.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "clinic", schema = "public", catalog = "postgres")
public class ClinicEntity {
    private int id;
    private String clinicName;
    private String address;
    private Collection<AppUserEntity> appUsersById;
    private Collection<CustomerEntity> customersById;
    private Collection<ReportFormEntity> reportFormsById;

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
    @Column(name = "clinic_name")
    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClinicEntity that = (ClinicEntity) o;
        return id == that.id &&
                Objects.equals(clinicName, that.clinicName) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clinicName, address);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicByClinicId")
    public Collection<AppUserEntity> getAppUsersById() {
        return appUsersById;
    }

    public void setAppUsersById(Collection<AppUserEntity> appUsersById) {
        this.appUsersById = appUsersById;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicByClinicId")
    public Collection<CustomerEntity> getCustomersById() {
        return customersById;
    }

    public void setCustomersById(Collection<CustomerEntity> customersById) {
        this.customersById = customersById;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicByClinicId")
    public Collection<ReportFormEntity> getReportFormsById() {
        return reportFormsById;
    }

    public void setReportFormsById(Collection<ReportFormEntity> reportFormsById) {
        this.reportFormsById = reportFormsById;
    }
}
