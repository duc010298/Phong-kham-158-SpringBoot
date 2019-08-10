package com.github.duc010298.clinic_manager.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "report_form", schema = "public", catalog = "postgres")
public class ReportFormEntity {
    private UUID id;
    private String reportName;
    private int orderNumber;
    private String content;

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
    @Column(name = "report_name")
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Basic
    @Column(name = "order_number")
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportFormEntity that = (ReportFormEntity) o;
        return orderNumber == that.orderNumber &&
                Objects.equals(id, that.id) &&
                Objects.equals(reportName, that.reportName) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reportName, orderNumber, content);
    }
}
