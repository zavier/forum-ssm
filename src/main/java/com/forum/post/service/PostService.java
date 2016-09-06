package com.forum.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.dao.PostMapper;
import com.forum.entity.Post;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    /**
     * 查找主题帖子下的所有帖子
     * 
     * @param topicId
     * @return
     */
    @Transactional(readOnly = true)
    public List<Post> getPagedPosts(String topicId) {

        return postMapper.selectByTopicId(topicId);
    }
}
