package com.study.board.service;

import com.study.board.domain.Board;
import com.study.board.domain.BoardSqlParameter;
import com.study.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Board> findBoardList(BoardSqlParameter boardSqlParameter) {
        return boardMapper.findBoardList(boardSqlParameter.getSearchType(), boardSqlParameter.getSearchKeyword(), boardSqlParameter.getOffset(), boardSqlParameter.getPerPage());
    }
}
