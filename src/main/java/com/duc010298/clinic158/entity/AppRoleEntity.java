package com.duc010298.clinic158.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "app_role", schema = "clinic", catalog = "")
public class AppRoleEntity {
    private long roleId;
    private String roleName;
    private Set<AppUserEntity> appUserEntities = new HashSet<>(0);

    @Id
    @Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setAppUserEntities(Set<AppUserEntity> appUserEntities) {
        this.appUserEntities = appUserEntities;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "role_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false)})
    public Set<AppUserEntity> getAppUserEntities() {
        return this.appUserEntities;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRoleEntity that = (AppRoleEntity) o;
        return roleId == that.roleId &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
