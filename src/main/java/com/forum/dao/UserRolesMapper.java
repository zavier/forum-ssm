package com.forum.dao;

import com.forum.entity.UserRolesKey;

public interface UserRolesMapper {
    int deleteByPrimaryKey(UserRolesKey key);

    int insert(UserRolesKey record);

    int insertSelective(UserRolesKey record);
}