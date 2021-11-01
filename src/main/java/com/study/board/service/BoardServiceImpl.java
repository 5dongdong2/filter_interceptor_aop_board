package com.study.board.service;

import com.study.board.domain.*;
import com.study.board.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
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
    public void writeBoard(BoardWrite boardWrite) {
        boardMapper.writeBoard(boardWrite);
    }

    @Override
    public void deleteBoard(Long board_idx) {
        boardMapper.deleteBoard(board_idx);
    }

    @Override
    public void updateBoard(BoardUpdate boardUpdate) {
        boardMapper.updateBoard(boardUpdate);
    }

    @Override
    public void likeAndDislike(BoardLikeDislike boardLikeDislike) {
        Integer check = boardMapper.checkLikeDislike(boardLikeDislike);
        if (check >= 1) {
            System.out.println("좋아요 취소");
            boardMapper.deleteLikeDislike(boardLikeDislike);
            boardMapper.updateBoardLikeDislike(boardLikeDislike);
        } else if (check == 0) {
            System.out.println("좋아요");
            boardMapper.insertLikeDislike(boardLikeDislike);
            boardMapper.updateBoardLikeDislike(boardLikeDislike);
        }
    }
}
