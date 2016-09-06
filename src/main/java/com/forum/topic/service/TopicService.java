package com.forum.topic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.dao.BoardMapper;
import com.forum.dao.TopicMapper;
import com.forum.entity.Board;
import com.forum.entity.Topic;
import com.forum.entity.User;
import com.forum.user.service.UserService;
import com.forum.util.entity.TopicView;

@Service
public class TopicService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserService userService;

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

    /**
     * 列出板块的所有主体帖子视图（TopicView集合）
     * 
     * @param boardId 板块id
     * @return
     */
    public List<TopicView> getTopicViewByBoardId(String boardId) {
        List<TopicView> topicViewList = new ArrayList<>();
        List<Topic> topicList = getPagedTopics(boardId);
        for (Topic topic : topicList) {
            String userId = topic.getUserId();
            User user = userService.findById(userId);

            TopicView topicView = new TopicView();
            topicView.setTopicId(topic.getId());
            topicView.setTopicTitle(topic.getTopicTitle());
            topicView.setUserName(user.getUserName());
            topicView.setPictureUrl(user.getPictureUrl());
            topicViewList.add(topicView);
        }
        return topicViewList;
    }

    /**
     * 查询主体帖子视图（单个TopicView）
     * 
     * @param topicId 主题帖id
     * @return
     */
    public TopicView getTopicViewByTopicId(String topicId) {
        Topic topic = getTopicById(topicId);
        String userId = topic.getUserId();
        User user = userService.findById(userId);

        TopicView topicView = new TopicView();
        topicView.setTopicId(topic.getId());
        topicView.setTopicTitle(topic.getTopicTitle());
        topicView.setUserName(user.getUserName());
        topicView.setPictureUrl(user.getPictureUrl());
        topicView.setCreateTime(topic.getCreateTime());
        topicView.setTopicContent(topic.getTopicContent());

        return topicView;
    }

}
