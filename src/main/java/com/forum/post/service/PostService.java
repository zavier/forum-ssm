package com.forum.post.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.dao.PostMapper;
import com.forum.entity.Post;
import com.forum.util.UUIDUtil;

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
    public List<Post> getPostsByTopicId(String topicId) {

        return postMapper.selectByTopicId(topicId);
    }

    @Transactional
    public void addPost(Post post) {
        String primaryKey = UUIDUtil.getUUIDPrimaryKey();
        post.setId(primaryKey);
        post.setCreateTime(new Date());
        postMapper.insertSelective(post);
    }
}
