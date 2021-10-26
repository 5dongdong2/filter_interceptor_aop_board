package com.study.board.controller;

import com.study.board.domain.Board;
import com.study.board.domain.BoardSqlParameter;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/${searchType}/${searchKeyword}")
    public List<Board> boardList(@PathVariable("searchType") String searchType, @PathVariable("searchKeyword") String searchKeyword, HttpServletRequest request) {
        int curPage = Integer.parseInt(request.getParameter("page"));
        int perPage = 10;
        BoardSqlParameter boardSqlParameter = new BoardSqlParameter();
        boardSqlParameter.setSearchType(searchType);
        boardSqlParameter.setSearchKeyword(searchKeyword);
        boardSqlParameter.setPerPage(perPage);
        boardSqlParameter.setOffset((curPage-1)*perPage);
        return boardService.findBoardList(boardSqlParameter);
    }
}
