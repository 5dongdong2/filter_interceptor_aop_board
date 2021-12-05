package com.study.board.service;

import com.study.board.domain.board.*;
import com.study.board.dto.board.BoardDto;
import com.study.board.dto.board.BoardUpdateDto;
import com.study.board.dto.board.BoardWriteDto;
import com.study.board.dto.board.PageAndSearchDto;

import java.util.List;

public interface BoardService {

    public List<BoardDto> findBoards(PageAndSearchDto pageAndSearchDto);

    public List<BoardDto> findBoardsWithSearch(PageAndSearchDto pageAndSearchDto);

    public BoardDto findBoardDetail(Long boardIdx);
    
    public void writeBoard(BoardWriteDto boardWriteDto);

    public void deleteBoard(Long boardIdx);

    public void updateBoard(BoardUpdateDto boardUpdateDto);

    public void likeAndDislikeBoard(BoardLikeDislike boardLikeDislike);
}
