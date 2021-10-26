package com.study.board.service;

import com.study.board.domain.Board;
import com.study.board.domain.BoardSqlParameter;

import java.util.List;

public interface BoardService {

    public List<Board> findBoardList(BoardSqlParameter boardSqlParameter);
}
