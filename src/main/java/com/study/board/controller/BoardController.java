package com.study.board.controller;

import com.study.board.domain.*;
import com.study.board.dto.BoardLikeDislikeDto;
import com.study.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param request
     * @return
     */
    @GetMapping("/boards")
    public List<Board> getBoardList(HttpServletRequest request) {
        Integer curPage = Integer.parseInt(request.getParameter("page"));
        Integer perpage = 10;

        PagingAndSearchingSqlParameter boardSqlParameter = new PagingAndSearchingSqlParameter();
        boardSqlParameter.setPerPage(perpage);
        boardSqlParameter.setOffset((curPage - 1) * perpage);
        return boardService.findBoardList(boardSqlParameter);
    }

    /**
     * 게시판 리스트 검색
     *
     * @param searchKeyword
     * @param request
     * @return
     */
    @GetMapping("/boards/search/{searchKeyword}")
    public List<Board> getBoardListWithSearch(@PathVariable("searchKeyword") String searchKeyword, HttpServletRequest request) {
        String searchType = request.getParameter("type");
        Integer curPage = Integer.parseInt(request.getParameter("page"));
        Integer perPage = 10;

        PagingAndSearchingSqlParameter boardSqlParameter = new PagingAndSearchingSqlParameter();
        boardSqlParameter.setSearchType(searchType);
        boardSqlParameter.setSearchKeyword(searchKeyword);
        boardSqlParameter.setPerPage(perPage);
        boardSqlParameter.setOffset((curPage - 1) * perPage);
        return boardService.findBoardListWithSearch(boardSqlParameter);
    }

    @GetMapping("/board/{board_idx}")
    public Board getBoardDetail(@PathVariable("board_idx") String board_idx) {
        return boardService.findBoardDetailByIdx(Long.valueOf(board_idx));
    }

    @PutMapping("/board")
    public void writeBoard(@Validated @RequestBody BoardWrite boardWrite, BindingResult bindingResult) {
        boardService.writeBoard(boardWrite);
    }

    @DeleteMapping("/board/{board_idx}")
    public void deleteBoard(@PathVariable("board_idx") String board_idx) {
        boardService.deleteBoard(Long.valueOf(board_idx));
    }

    @PutMapping("/board/{board_idx}")
    public void updateBoard(@PathVariable("board_idx") String board_idx, @Validated @RequestBody BoardUpdate boardUpdate, BindingResult bindingResult) {
        boardUpdate.setBoard_idx(board_idx);
        boardService.updateBoard(boardUpdate);
    }

    @PostMapping("/board/like")
    public void like(@Validated @RequestBody BoardLikeDislikeDto boardLikeDislikeDto, BindingResult bindingResult) {
        BoardLikeDislike boardLikeDislike = new BoardLikeDislike();
        boardLikeDislike.setMember_idx(Long.valueOf(boardLikeDislikeDto.getMember_idx()));
        boardLikeDislike.setBoard_idx(Long.valueOf(boardLikeDislikeDto.getBoard_idx()));
        boardLikeDislike.setBoard_like_dislike(String.valueOf(LikeAndDislike.LIKE.ordinal()));
        boardService.likeAndDislike(boardLikeDislike);
    }

    @PostMapping("/board/dislike")
    public void dislike(@Validated @RequestBody BoardLikeDislikeDto boardLikeDislikeDto, BindingResult bindingResult) {
        BoardLikeDislike boardLikeDislike = new BoardLikeDislike();
        boardLikeDislike.setMember_idx(Long.valueOf(boardLikeDislikeDto.getMember_idx()));
        boardLikeDislike.setBoard_idx(Long.valueOf(boardLikeDislikeDto.getBoard_idx()));
        boardLikeDislike.setBoard_like_dislike(String.valueOf(LikeAndDislike.DISLIKE.ordinal()));
        boardService.likeAndDislike(boardLikeDislike);
    }
}
