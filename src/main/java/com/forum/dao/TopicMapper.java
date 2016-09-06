package com.forum.dao;

import java.util.List;

import com.forum.entity.Topic;

public interface TopicMapper {
    int deleteByPrimaryKey(String id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(String id);

    List<Topic> selectByBoardId(String boardId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}
