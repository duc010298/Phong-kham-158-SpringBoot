package com.duc010298.phongkham158.entity;

public class AppRoleEntity {

    private Long roleId;
    private String roleName;

    public AppRoleEntity() {
    }

    public AppRoleEntity(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
