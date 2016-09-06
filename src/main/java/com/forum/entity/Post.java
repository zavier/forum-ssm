package com.forum.entity;

import java.util.Date;

public class Post {
    private String id;

    private Date createTime;

    private String postText;

    private String postTitle;

    private Integer postType;

    private String boardId;

    private String topicId;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText == null ? null : postText.trim();
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId == null ? null : boardId.trim();
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", createTime=" + createTime + ", postText=" + postText
                + ", postTitle=" + postTitle + ", postType=" + postType + ", boardId=" + boardId
                + ", topicId=" + topicId + ", userId=" + userId + "]";
    }
}
