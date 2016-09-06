package com.forum.dao;

import java.util.List;

import com.forum.entity.Post;

public interface PostMapper {
    int deleteByPrimaryKey(String id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(String id);

    List<Post> selectByTopicId(String topicId);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
}
