package com.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.dao.BoardMapper;
import com.forum.dao.PostMapper;
import com.forum.dao.TopicMapper;
import com.forum.entity.Board;
import com.forum.entity.Post;
import com.forum.entity.Topic;
import com.forum.util.CommonUtil;

@Service
public class ForumService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private PostMapper postMapper;

    // 查询所有的论坛模块
    @Transactional(readOnly = true)
    public List<Board> getAllBoards() {
        return boardMapper.selectAll();
    }

    // 添加论坛板块
    @Transactional
    public void addBoard(Board board) {
        board.setId(CommonUtil.getUUIDPrimaryKey());
        board.setTopicNum(0);
        boardMapper.insert(board);
    }

    // 更新论坛板块
    @Transactional
    public void updateBoard(Board board) {
        board.setTopicNum(0);
        boardMapper.updateByPrimaryKeySelective(board);
    }

    // 删除论坛版块
    @Transactional
    public void deleteBoard(String boardId) {
        List<Topic> topics = topicMapper.selectByBoardId(boardId);
        for (Topic topic : topics) {
            topicMapper.deleteByPrimaryKey(topic.getId());
        }
        Board board = boardMapper.selectByPrimaryKey(boardId);
        boardMapper.deleteByPrimaryKey(board.getId());
    }

    // 通过ID查找板块
    @Transactional(readOnly = true)
    public Board getBoardById(String boardId) {
        return boardMapper.selectByPrimaryKey(boardId);
    }

    // 查找板块下所有主题帖
    @Transactional(readOnly = true)
    public List<Topic> getPagedTopics(String boardId) {
        return topicMapper.selectByBoardId(boardId);
    }

    // 增加主题帖
    @Transactional
    public void addTopic(Topic topic, String boardId) {
        Board board = boardMapper.selectByPrimaryKey(boardId);
        topic.setBoardId(board.getId());
        topicMapper.insert(topic);
    }

    // 根据主题帖ID查找主题帖
    @Transactional(readOnly = true)
    public Topic getTopicById(String topicId) {

        return topicMapper.selectByPrimaryKey(topicId);
    }

    // 查找主题帖子下的所有帖子
    @Transactional(readOnly = true)
    public List<Post> getPagedPosts(String topicId) {

        return postMapper.selectByTopicId(topicId);
    }

    @Transactional(readOnly = true)
    public boolean ifExistBoardName(Board board) {
        Board searchBoard = boardMapper.selectByBoardName(board.getBoardName());
        if (searchBoard != null) {
            return true;
        }
        return false;
    }
}
