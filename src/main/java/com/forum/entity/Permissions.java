package com.forum.entity;

public class Permissions {
    private String id;

    private String permission;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    @Override
    public String toString() {
        return "Permissions [id=" + id + ", permission=" + permission + ", desc=" + desc + "]";
    }

}
