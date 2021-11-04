package com.study.board.service;

import com.study.board.domain.board.*;
import com.study.board.dto.board.BoardDto;
import com.study.board.dto.board.BoardUpdateDto;
import com.study.board.dto.board.BoardWriteDto;
import com.study.board.dto.board.SearchAndPagingDto;

import java.util.List;

public interface BoardService {

    /**
     * 게시글 리스트
     * @param searchAndPagingDto
     * @return
     */
    public List<BoardDto> findBoards(SearchAndPagingDto searchAndPagingDto);

    /**
     * 게시글 리스트 검색
     * @param searchAndPagingDto
     * @return
     */
    public List<BoardDto> findBoardsWithSearch(SearchAndPagingDto searchAndPagingDto);

    /**
     * 상세페이지
     * @param board_idx
     * @return
     */
    public BoardDto findBoardDetail(Long board_idx);
    
    /**
     * 게시글 작성
     * @param boardWriteDto
     */
    public void writeBoard(BoardWriteDto boardWriteDto);

    /**
     * 게시글 삭제
     * @param board_idx
     */
    public void deleteBoard(Long board_idx);

    /**
     * 게시글 수정
     * @param boardUpdateDto
     */
    public void updateBoard(BoardUpdateDto boardUpdateDto);

    /**
     * 좋아요 및 싫어요
     * @param boardLikeDislike
     */
    public void likeAndDislikeBoard(BoardLikeDislike boardLikeDislike);
}
