package com.forum.dao;

import java.util.List;

import com.forum.entity.Board;

public interface BoardMapper {
    int deleteByPrimaryKey(String id);

    int insert(Board record);

    int insertSelective(Board record);

    Board selectByPrimaryKey(String id);

    Board selectByBoardName(String boardName);

    List<Board> selectAll();

    int updateByPrimaryKeySelective(Board record);

    int updateByPrimaryKey(Board record);
}
