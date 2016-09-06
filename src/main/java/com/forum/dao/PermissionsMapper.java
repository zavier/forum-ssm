package com.forum.dao;

import com.forum.entity.Permissions;

public interface PermissionsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);
}