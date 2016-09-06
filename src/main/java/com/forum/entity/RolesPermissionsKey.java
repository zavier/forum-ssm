package com.forum.entity;

public class RolesPermissionsKey {
    private String roleId;

    private String permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }

    @Override
    public String toString() {
        return "RolesPermissionsKey [roleId=" + roleId + ", permissionId=" + permissionId + "]";
    }
}
