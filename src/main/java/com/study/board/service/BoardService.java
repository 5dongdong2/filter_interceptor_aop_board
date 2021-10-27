package com.study.board.service;

import com.study.board.domain.Board;
import com.study.board.domain.BoardSqlParameter;

import java.util.List;

public interface BoardService {

    /**
     * page
     * @param boardSqlParameter
     * @return
     */
    public List<Board> findBoardList(BoardSqlParameter boardSqlParameter);

    /**
     * 검색 + page
     * @param boardSqlParameter
     * @return
     */
    public List<Board> findBoardListWithSearch(BoardSqlParameter boardSqlParameter);
}
