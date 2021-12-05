package com.study.board.service;

import com.study.board.domain.board.*;
import com.study.board.dto.board.BoardDto;
import com.study.board.dto.board.BoardUpdateDto;
import com.study.board.dto.board.BoardWriteDto;
import com.study.board.dto.board.PageAndSearchDto;
import com.study.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<BoardDto> findBoards(PageAndSearchDto pageAndSearchDto) {
        return boardMapper.findBoards(pageAndSearchDto.getOffset(), pageAndSearchDto.getPerPage());
    }

    @Override
    public List<BoardDto> findBoardsWithSearch(PageAndSearchDto pageAndSearchDto) {
        return boardMapper.findBoardsWithSearch(pageAndSearchDto.getSearchType(), pageAndSearchDto.getSearchKeyword(), pageAndSearchDto.getOffset(), pageAndSearchDto.getPerPage());
    }

    @Override
    public BoardDto findBoardDetail(Long boardIdx) {
        return boardMapper.findBoardDetail(boardIdx);
    }

    @Override
    public void writeBoard(BoardWriteDto boardWriteDto) {
        BoardWrite boardWrite = BoardWrite.builder()
                .memberIdx(boardWriteDto.getMemberIdx())
                .boardTitle(boardWriteDto.getBoardTitle())
                .boardContent(boardWriteDto.getBoardContent())
                .build();
        boardMapper.insertBoard(boardWrite);
    }

    @Override
    public void deleteBoard(Long boardIdx) {
        boardMapper.deleteBoard(boardIdx);
    }

    @Override
    public void updateBoard(BoardUpdateDto boardUpdateDto) {
        BoardUpdate boardUpdate = BoardUpdate.builder()
                .boardIdx(boardUpdateDto.getBoardIdx())
                .boardTitle(boardUpdateDto.getBoardTitle())
                .boardContent(boardUpdateDto.getBoardContent())
                .build();
        boardMapper.updateBoard(boardUpdate);
    }

    @Transactional
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
