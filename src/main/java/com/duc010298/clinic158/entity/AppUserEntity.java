package com.duc010298.clinic158.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "app_user", schema = "clinic", catalog = "")
public class AppUserEntity {
    private long userId;
    private String userName;
    private String encryptedPassword;
    private Set<AppRoleEntity> appRoleEntities = new HashSet<>(0);

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "encrypted_password")
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "appUserEntities")
    public Set<AppRoleEntity> getAppRoleEntities() {
        return this.appRoleEntities;
    }

    public void setAppRoleEntities(Set<AppRoleEntity> appRoleEntities) {
        this.appRoleEntities = appRoleEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserEntity that = (AppUserEntity) o;
        return userId == that.userId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(encryptedPassword, that.encryptedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, encryptedPassword);
    }
}
