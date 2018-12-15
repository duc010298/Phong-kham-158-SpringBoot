package com.duc010298.phongkham158.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "approle", schema = "clinic", catalog = "")
public class ApproleEntity {
    private long roleId;
    private String roleName;

    @Id
    @Column(name = "roleId")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "roleName")
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
        ApproleEntity that = (ApproleEntity) o;
        return roleId == that.roleId &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
