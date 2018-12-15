package com.duc010298.phongkham158.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "appuser", schema = "clinic", catalog = "")
public class AppuserEntity {
    private long userId;
    private String userName;
    private String encrytedPassword;

    @Id
    @Column(name = "userId")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "encrytedPassword")
    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppuserEntity that = (AppuserEntity) o;
        return userId == that.userId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(encrytedPassword, that.encrytedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, encrytedPassword);
    }
}
