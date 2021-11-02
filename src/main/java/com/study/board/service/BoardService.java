package com.study.board.service;

import com.study.board.domain.board.*;

import java.util.List;

public interface BoardService {

    /**
     * 게시글 리스트
     * @param boardSqlParameter
     * @return
     */
    public List<Board> findBoards(SearchAndPaging boardSqlParameter);

    /**
     * 게시글 리스트 검색
     * @param boardSqlParameter
     * @return
     */
    public List<Board> findBoardsWithSearch(SearchAndPaging boardSqlParameter);

    /**
     * 상세페이지
     * @param board_idx
     * @return
     */
    public Board findBoardDetail(Long board_idx);
    
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
     * 게시글 수정
     * @param boardUpdate
     */
    public void updateBoard(BoardUpdate boardUpdate);

    /**
     * 좋아요 및 싫어요
     * @param boardLikeDislike
     */
    public void likeAndDislikeBoard(BoardLikeDislike boardLikeDislike);
}
