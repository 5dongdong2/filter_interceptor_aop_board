package com.study.board.controller;

import com.study.board.domain.Board;
import com.study.board.domain.BoardSqlParameter;
import com.study.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 게시판 리스트 미검색
     * @param request
     * @return
     */
    @GetMapping("/boards")
    public List<Board> boardList(HttpServletRequest request) {
        Integer curPage = Integer.parseInt(request.getParameter("page"));
        Integer perpage = 10;

        BoardSqlParameter boardSqlParameter = new BoardSqlParameter();
        boardSqlParameter.setPerPage(perpage);
        boardSqlParameter.setOffset((curPage-1)*perpage);
        return boardService.findBoardList(boardSqlParameter);
    }

    /**
     * 게시판 리스트 검색
     * @param searchKeyword
     * @param request
     * @return
     */
    @GetMapping("/boards/search/{searchKeyword}")
    public List<Board> boardListSearch(@PathVariable("searchKeyword") String searchKeyword, HttpServletRequest request) {
        String searchType = request.getParameter("type");
        Integer curPage = Integer.parseInt(request.getParameter("page"));
        Integer perPage = 10;

        BoardSqlParameter boardSqlParameter = new BoardSqlParameter();
        boardSqlParameter.setSearchType(searchType);
        boardSqlParameter.setSearchKeyword(searchKeyword);
        boardSqlParameter.setPerPage(perPage);
        boardSqlParameter.setOffset((curPage-1)*perPage);
        return boardService.findBoardListWithSearch(boardSqlParameter);
    }
}
