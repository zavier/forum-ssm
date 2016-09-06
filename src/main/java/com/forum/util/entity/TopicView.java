package com.forum.util.entity;

import java.util.Date;

/**
 * 页面展示的主题帖对应的类(主题帖与用户集)
 *
 */
public class TopicView {
    // --------------------Topic----------------------
    private String topicId;

    private Date createTime;

    private Integer digest; // 为0不是精华帖,1为精华帖

    private Date lastPost;

    private String topicContent;

    private Integer topicReplies;

    private String topicTitle;

    private Integer topicView;

    private String boardId;

    // --------------------User----------------------
    private String userId;

    private Integer credit;

    private Integer locked;

    private String password;

    private String pictureUrl;

    private String userName;

    private Integer userType;

    // ------------------------------------------------

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
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
        this.topicContent = topicContent;
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
        this.topicTitle = topicTitle;
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
        this.boardId = boardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "TopicView [topicId=" + topicId + ", createTime=" + createTime + ", digest=" + digest
                + ", lastPost=" + lastPost + ", topicContent=" + topicContent + ", topicReplies="
                + topicReplies + ", topicTitle=" + topicTitle + ", topicView=" + topicView
                + ", boardId=" + boardId + ", userId=" + userId + ", credit=" + credit + ", locked="
                + locked + ", password=" + password + ", pictureUrl=" + pictureUrl + ", userName="
                + userName + ", userType=" + userType + ", getTopicId()=" + getTopicId()
                + ", getCreateTime()=" + getCreateTime() + ", getDigest()=" + getDigest()
                + ", getLastPost()=" + getLastPost() + ", getTopicContent()=" + getTopicContent()
                + ", getTopicReplies()=" + getTopicReplies() + ", getTopicTitle()="
                + getTopicTitle() + ", getTopicView()=" + getTopicView() + ", getBoardId()="
                + getBoardId() + ", getUserId()=" + getUserId() + ", getCredit()=" + getCredit()
                + ", getLocked()=" + getLocked() + ", getPassword()=" + getPassword()
                + ", getPictureUrl()=" + getPictureUrl() + ", getUserName()=" + getUserName()
                + ", getUserType()=" + getUserType() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
