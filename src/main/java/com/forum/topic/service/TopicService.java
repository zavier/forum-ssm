package com.forum.topic.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(TopicService.class);
    
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
            topicView.setTopic(topic);
            topicView.setUser(user);
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
        log.info("查找到的主题帖为：" + topic);
        String userId = topic.getUserId();
        User user = userService.findById(userId);
        log.info("通过主题帖查找到的用户为：" + user);

        TopicView topicView = new TopicView();
        topicView.setTopic(topic);
        topicView.setUser(user);

        return topicView;
    }

}
