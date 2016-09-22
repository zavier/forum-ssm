package com.forum.util.entity;

import com.forum.entity.Post;
import com.forum.entity.User;

/**
 * 页面展示的评论帖对应的类(评论帖与对应用户)
 *
 */
public class PostView {
    
    private Post post;
    
    private User user;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PostView [post=" + post + ", user=" + user + "]";
    }
    
    
}
