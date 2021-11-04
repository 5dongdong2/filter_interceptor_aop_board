package com.study.board.service;

import com.study.board.domain.board.*;
import com.study.board.dto.board.BoardDto;
import com.study.board.dto.board.BoardUpdateDto;
import com.study.board.dto.board.BoardWriteDto;
import com.study.board.dto.board.SearchAndPagingDto;
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
    public List<BoardDto> findBoards(SearchAndPagingDto searchAndPagingDto) {
        return boardMapper.findBoards(searchAndPagingDto.getOffset(), searchAndPagingDto.getPerPage());
    }

    @Override
    public List<BoardDto> findBoardsWithSearch(SearchAndPagingDto searchAndPagingDto) {
        return boardMapper.findBoardsWithSearch(searchAndPagingDto.getSearchType(), searchAndPagingDto.getSearchKeyword(), searchAndPagingDto.getOffset(), searchAndPagingDto.getPerPage());
    }

    @Override
    public BoardDto findBoardDetail(Long board_idx) {
        return boardMapper.findBoardDetail(board_idx);
    }

    @Override
    public void writeBoard(BoardWriteDto boardWriteDto) {
        BoardWrite boardWrite = BoardWrite.builder()
                .member_idx(boardWriteDto.getMember_idx())
                .board_title(boardWriteDto.getBoard_title())
                .board_content(boardWriteDto.getBoard_content())
                .build();
        boardMapper.insertBoard(boardWrite);
    }

    @Override
    public void deleteBoard(Long board_idx) {
        boardMapper.deleteBoard(board_idx);
    }

    @Override
    public void updateBoard(BoardUpdateDto boardUpdateDto) {
        BoardUpdate boardUpdate = BoardUpdate.builder()
                .board_idx(boardUpdateDto.getBoard_idx())
                .board_title(boardUpdateDto.getBoard_title())
                .board_content(boardUpdateDto.getBoard_content())
                .build();
        boardMapper.updateBoard(boardUpdate);
    }

    @Override
    public void likeAndDislikeBoard(BoardLikeDislike boardLikeDislike) {
        Integer check = boardMapper.checkLikeDislike(boardLikeDislike);
        if (check >= 1) {
            boardMapper.deleteLikeDislike(boardLikeDislike);
            boardMapper.updateBoardLikeDislike(boardLikeDislike);
        } else if (check == 0) {
            boardMapper.insertLikeDislike(boardLikeDislike);
            boardMapper.updateBoardLikeDislike(boardLikeDislike);
        }
    }
}
