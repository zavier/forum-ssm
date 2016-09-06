package com.forum.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.dao.BoardMapper;
import com.forum.dao.TopicMapper;
import com.forum.entity.Board;
import com.forum.entity.Topic;
import com.forum.util.UUIDUtil;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private TopicMapper topicMapper;

    /**
     * 查询所有的论坛模块
     */
    @Transactional(readOnly = true)
    public List<Board> getAllBoards() {
        return boardMapper.selectAll();
    }

    /**
     * 添加论坛板块
     */
    @Transactional
    public void addBoard(Board board) {
        board.setId(UUIDUtil.getUUIDPrimaryKey());
        board.setTopicNum(0);
        boardMapper.insert(board);
    }

    /**
     * 更新论坛板块
     * 
     * @param board
     */
    @Transactional
    public void updateBoard(Board board) {
        board.setTopicNum(0);
        boardMapper.updateByPrimaryKeySelective(board);
    }

    /**
     * 删除论坛版块
     * 
     * @param boardId
     */
    @Transactional
    public void deleteBoard(String boardId) {
        List<Topic> topics = topicMapper.selectByBoardId(boardId);
        for (Topic topic : topics) {
            topicMapper.deleteByPrimaryKey(topic.getId());
        }
        Board board = boardMapper.selectByPrimaryKey(boardId);
        boardMapper.deleteByPrimaryKey(board.getId());
    }

    /**
     * 通过ID查找板块
     * 
     * @param boardId
     * @return
     */
    @Transactional(readOnly = true)
    public Board getBoardById(String boardId) {
        return boardMapper.selectByPrimaryKey(boardId);
    }

    /**
     * 是否存在板块名
     * 
     * @param board
     * @return
     */
    @Transactional(readOnly = true)
    public boolean ifExistBoardName(Board board) {
        Board searchBoard = boardMapper.selectByBoardName(board.getBoardName());
        if (searchBoard != null) {
            return true;
        }
        return false;
    }

}
