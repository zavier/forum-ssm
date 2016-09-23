package com.forum.entity;

import javax.validation.constraints.NotNull;

public class Board {
    private String id;

    private String boardDesc;

    @NotNull
    private String boardName;

    private Integer topicNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc == null ? null : boardDesc.trim();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName == null ? null : boardName.trim();
    }

    public Integer getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(Integer topicNum) {
        this.topicNum = topicNum;
    }

    @Override
    public String toString() {
        return "Board [id=" + id + ", boardDesc=" + boardDesc + ", boardName=" + boardName
                + ", topicNum=" + topicNum + "]";
    }

}
