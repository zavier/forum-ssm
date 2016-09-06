package com.forum.dao;

import com.forum.entity.RolesPermissionsKey;

public interface RolesPermissionsMapper {
    int deleteByPrimaryKey(RolesPermissionsKey key);

    int insert(RolesPermissionsKey record);

    int insertSelective(RolesPermissionsKey record);
}