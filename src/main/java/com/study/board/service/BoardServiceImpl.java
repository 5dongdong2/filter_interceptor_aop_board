package com.study.board.service;

import com.study.board.domain.Board;
import com.study.board.domain.PagingAndSearchingSqlParameter;
import com.study.board.domain.WriteParameter;
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
    public List<Board> findBoardList(PagingAndSearchingSqlParameter boardSqlParameter) {
        return boardMapper.findBoardList(boardSqlParameter.getOffset(), boardSqlParameter.getPerPage());
    }

    @Override
    public List<Board> findBoardListWithSearch(PagingAndSearchingSqlParameter boardSqlParameter) {
        return boardMapper.findBoardListWithSearch(boardSqlParameter.getSearchType(), boardSqlParameter.getSearchKeyword(), boardSqlParameter.getOffset(), boardSqlParameter.getPerPage());
    }

    @Override
    public Board findBoardDetailByIdx(Long board_idx) {
        return boardMapper.findBoardDetailByIdx(board_idx);
    }

    @Override
    public void writeBoard(WriteParameter writeParameter) {
        boardMapper.writeBoard(writeParameter);
    }
}
