package com.study.board.service;

import com.study.board.domain.board.*;
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
    public List<Board> findBoards(SearchAndPaging boardSqlParameter) {
        return boardMapper.findBoards(boardSqlParameter.getOffset(), boardSqlParameter.getPerPage());
    }

    @Override
    public List<Board> findBoardsWithSearch(SearchAndPaging boardSqlParameter) {
        return boardMapper.findBoardsWithSearch(boardSqlParameter.getSearchType(), boardSqlParameter.getSearchKeyword(), boardSqlParameter.getOffset(), boardSqlParameter.getPerPage());
    }

    @Override
    public Board findBoardDetail(Long board_idx) {
        return boardMapper.findBoardDetail(board_idx);
    }

    @Override
    public void writeBoard(BoardWrite boardWrite) {
        boardMapper.insertBoard(boardWrite);
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
