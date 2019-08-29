package com.github.duc010298.clinic_manager.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "app_role", catalog = "postgres")
public class AppRoleEntity {
    private int id;
    private String roleName;
    private Collection<AppUserEntity> appUserEntities;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRoleEntity that = (AppRoleEntity) o;
        return id == that.id &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @ManyToMany(mappedBy = "appRoleEntities")
    public Collection<AppUserEntity> getAppUserEntities() {
        return appUserEntities;
    }

    public void setAppUserEntities(Collection<AppUserEntity> appRoleEntities) {
        this.appUserEntities = appRoleEntities;
    }
}
