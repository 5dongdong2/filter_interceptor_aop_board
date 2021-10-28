package com.study.board.service;

import com.study.board.domain.Board;
import com.study.board.domain.PagingAndSearchingSqlParameter;
import com.study.board.domain.WriteParameter;

import java.util.List;

public interface BoardService {

    /**
     * page
     * @param boardSqlParameter
     * @return
     */
    public List<Board> findBoardList(PagingAndSearchingSqlParameter boardSqlParameter);

    /**
     * 검색 + page
     * @param boardSqlParameter
     * @return
     */
    public List<Board> findBoardListWithSearch(PagingAndSearchingSqlParameter boardSqlParameter);

    /**
     * 게시글 작성
     * @param writeParameter
     */
    public void writeBoard(WriteParameter writeParameter);
}
