package com.forum.entity;

public class UserRolesKey {
    private String userId;

    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    @Override
    public String toString() {
        return "UserRolesKey [userId=" + userId + ", roleId=" + roleId + "]";
    }
}
