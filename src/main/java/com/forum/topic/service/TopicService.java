package com.forum.topic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.dao.BoardMapper;
import com.forum.dao.TopicMapper;
import com.forum.entity.Board;
import com.forum.entity.Topic;

@Service
public class TopicService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private TopicMapper topicMapper;

    /**
     * 查找板块下所有主题帖
     * 
     * @param boardId
     * @return
     */
    @Transactional(readOnly = true)
    public List<Topic> getPagedTopics(String boardId) {
        return topicMapper.selectByBoardId(boardId);
    }

    /**
     * 增加主题帖
     * 
     * @param topic
     * @param boardId
     */
    @Transactional
    public void addTopic(Topic topic, String boardId) {
        Board board = boardMapper.selectByPrimaryKey(boardId);
        topic.setBoardId(board.getId());
        topicMapper.insert(topic);
    }

    /**
     * 根据主题帖ID查找主题帖
     * 
     * @param topicId
     * @return
     */
    @Transactional(readOnly = true)
    public Topic getTopicById(String topicId) {

        return topicMapper.selectByPrimaryKey(topicId);
    }

}
