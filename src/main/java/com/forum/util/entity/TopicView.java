package com.forum.util.entity;

import com.forum.entity.Topic;
import com.forum.entity.User;

/**
 * 页面展示的主题帖对应的类(主题帖与用户)
 *
 */
public class TopicView {
    
    private Topic topic;
    
    private User user;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TopicView [topic=" + topic + ", user=" + user + "]";
    }
    
}
