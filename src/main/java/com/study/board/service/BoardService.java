package com.study.board.service;

import com.study.board.domain.board.*;

import java.util.List;

public interface BoardService {

    /**
     * page
     * @param boardSqlParameter
     * @return
     */
    public List<Board> findBoards(SearchAndPaging boardSqlParameter);

    /**
     * 검색 + page
     * @param boardSqlParameter
     * @return
     */
    public List<Board> findBoardsWithSearch(SearchAndPaging boardSqlParameter);

    /**
     * 상세페이지
     * @param board_idx
     * @return
     */
    public Board findBoardDetailByIdx(Long board_idx);
    
    /**
     * 게시글 작성
     * @param boardWrite
     */
    public void writeBoard(BoardWrite boardWrite);

    /**
     * 게시글 삭제
     * @param board_idx
     */
    public void deleteBoard(Long board_idx);

    /**
     * 게시글 업데이트
     * @param boardUpdate
     */
    public void updateBoard(BoardUpdate boardUpdate);

    /**
     * 좋아요, 싫어요
     * @param boardLikeDislike
     */
    public void likeAndDislike(BoardLikeDislike boardLikeDislike);
}
