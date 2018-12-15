package com.duc010298.phongkham158.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reportform", schema = "clinic", catalog = "")
public class ReportformEntity {
    private int id;
    private String name;
    private int orderNumber;
    private String content;

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
    @Column(name = "orderNumber")
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
        ReportformEntity that = (ReportformEntity) o;
        return id == that.id &&
                orderNumber == that.orderNumber &&
                Objects.equals(name, that.name) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, orderNumber, content);
    }
}
