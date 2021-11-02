package com.study.board.controller;

import com.study.board.domain.LikeAndDislike;
import com.study.board.domain.board.*;
import com.study.board.domain.comment.CommentWrite;
import com.study.board.dto.BoardLikeDislikeDto;
import com.study.board.dto.board.BoardUpdateDto;
import com.study.board.dto.board.BoardWriteDto;
import com.study.board.dto.comment.CommentWriteDto;
import com.study.board.service.BoardService;
import com.study.board.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class BoardAndCommentController {

    private final BoardService boardService;

    private final CommentService commentService;

    @Autowired
    public BoardAndCommentController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    /*게시글*/

    /**
     * 게시판 리스트 미검색
     * @param request
     * @return
     */
    @GetMapping("/boards")
    public List<Board> getBoardList(HttpServletRequest request) {
        Integer curPage = Integer.parseInt(request.getParameter("page"));
        Integer perpage = 10;

        SearchAndPaging boardSqlParameter = new SearchAndPaging();
        boardSqlParameter.setPerPage(perpage);
        boardSqlParameter.setOffset((curPage - 1) * perpage);
        return boardService.findBoards(boardSqlParameter);
    }

    /**
     * 게시판 리스트 검색
     * @param searchKeyword
     * @param request
     * @return
     */
    @GetMapping("/boards/search/{searchKeyword}")
    public List<Board> getBoardListWithSearch(@PathVariable("searchKeyword") String searchKeyword, HttpServletRequest request) {
        String searchType = request.getParameter("type");
        Integer curPage = Integer.parseInt(request.getParameter("page"));
        Integer perPage = 10;

        SearchAndPaging searchAndPaging = new SearchAndPaging();
        searchAndPaging.setSearchType(searchType);
        searchAndPaging.setSearchKeyword(searchKeyword);
        searchAndPaging.setPerPage(perPage);
        searchAndPaging.setOffset((curPage - 1) * perPage);
        return boardService.findBoardsWithSearch(searchAndPaging);
    }

    /**
     * 상세페이지
     * @param board_idx
     * @return
     */
    @GetMapping("/board/{board_idx}")
    public Board getBoardDetail(@PathVariable("board_idx") String board_idx) {
        return boardService.findBoardDetailByIdx(Long.valueOf(board_idx));
    }

    /**
     * 게시글 작성
     * @param boardWriteDto
     * @param bindingResult
     */
    @PostMapping("/board")
    public void writeBoard(@Validated @RequestBody BoardWriteDto boardWriteDto, BindingResult bindingResult) {
        BoardWrite boardWrite = new BoardWrite();
        boardWrite.setMember_idx(Long.valueOf(boardWriteDto.getMember_idx()));
        boardWrite.setBoard_title(boardWriteDto.getBoard_title());
        boardWrite.setBoard_content(boardWriteDto.getBoard_content());
        boardService.writeBoard(boardWrite);
    }

    /**
     * 게시글 삭제
     * @param board_idx
     */
    @DeleteMapping("/board/{board_idx}")
    public void deleteBoard(@PathVariable("board_idx") String board_idx) {
        boardService.deleteBoard(Long.valueOf(board_idx));
    }

    /**
     * 게시글 수정
     * @param board_idx
     * @param boardUpdateDto
     * @param bindingResult
     */
    @PutMapping("/board/{board_idx}")
    public void updateBoard(@Validated @RequestBody BoardUpdateDto boardUpdateDto, BindingResult bindingResult,
                            @PathVariable("board_idx") String board_idx) {
        BoardUpdate boardUpdate = new BoardUpdate();
        boardUpdate.setBoard_idx(Long.valueOf(board_idx));
        boardUpdate.setBoard_title(boardUpdateDto.getBoard_title());
        boardUpdate.setBoard_content(boardUpdateDto.getBoard_content());
        boardService.updateBoard(boardUpdate);
    }

    /**
     * 게시글 좋아요
     * @param boardLikeDislikeDto
     * @param bindingResult
     */
    @PostMapping("/board/like")
    public void like(@Validated @RequestBody BoardLikeDislikeDto boardLikeDislikeDto, BindingResult bindingResult) {
        BoardLikeDislike boardLikeDislike = new BoardLikeDislike();
        boardLikeDislike.setMember_idx(Long.valueOf(boardLikeDislikeDto.getMember_idx()));
        boardLikeDislike.setBoard_idx(Long.valueOf(boardLikeDislikeDto.getBoard_idx()));
        boardLikeDislike.setBoard_like_dislike(String.valueOf(LikeAndDislike.LIKE.ordinal()));
        boardService.likeAndDislike(boardLikeDislike);
    }

    /**
     * 게시글 싫어요
     * @param boardLikeDislikeDto
     * @param bindingResult
     */
    @PostMapping("/board/dislike")
    public void dislike(@Validated @RequestBody BoardLikeDislikeDto boardLikeDislikeDto, BindingResult bindingResult) {
        BoardLikeDislike boardLikeDislike = new BoardLikeDislike();
        boardLikeDislike.setMember_idx(Long.valueOf(boardLikeDislikeDto.getMember_idx()));
        boardLikeDislike.setBoard_idx(Long.valueOf(boardLikeDislikeDto.getBoard_idx()));
        boardLikeDislike.setBoard_like_dislike(String.valueOf(LikeAndDislike.DISLIKE.ordinal()));
        boardService.likeAndDislike(boardLikeDislike);
    }

    /*Comment*/
    //댓글 조회
    /**
     * 댓글 작성
     * @param commentWriteDto
     */
    @PostMapping("/board/{board_idx}/comment")
    public void writeComment(@Validated @ModelAttribute CommentWriteDto commentWriteDto,
                             @PathVariable("board_idx") String board_idx) {
        CommentWrite commentWrite = new CommentWrite();
        commentWrite.setBoard_idx(Long.valueOf(board_idx));
        commentWrite.setMember_idx(Long.valueOf(commentWriteDto.getMember_idx()));
        commentWrite.setComment_content(commentWriteDto.getComment_content());
    }
    //댓글 삭제
    //댓글 수정
    //댓글 좋아요
    //댓글 싫어요
}
