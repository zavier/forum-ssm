package com.forum.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Topic {
    private String id;

    private Date createTime;

    private Integer digest; // 为0不是精华帖,1为精华帖

    private Date lastPost;

    @NotNull
    private String topicContent;

    private Integer topicReplies;

    @NotNull
    private String topicTitle;

    private Integer topicView;

    private String boardId;

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

    public Integer getDigest() {
        return digest;
    }

    public void setDigest(Integer digest) {
        this.digest = digest;
    }

    public Date getLastPost() {
        return lastPost;
    }

    public void setLastPost(Date lastPost) {
        this.lastPost = lastPost;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent == null ? null : topicContent.trim();
    }

    public Integer getTopicReplies() {
        return topicReplies;
    }

    public void setTopicReplies(Integer topicReplies) {
        this.topicReplies = topicReplies;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle == null ? null : topicTitle.trim();
    }

    public Integer getTopicView() {
        return topicView;
    }

    public void setTopicView(Integer topicView) {
        this.topicView = topicView;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId == null ? null : boardId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @Override
    public String toString() {
        return "Topic [id=" + id + ", createTime=" + createTime + ", digest=" + digest
                + ", lastPost=" + lastPost + ", topicContent=" + topicContent + ", topicReplies="
                + topicReplies + ", topicTitle=" + topicTitle + ", topicView=" + topicView
                + ", boardId=" + boardId + ", userId=" + userId + "]";
    }
}
