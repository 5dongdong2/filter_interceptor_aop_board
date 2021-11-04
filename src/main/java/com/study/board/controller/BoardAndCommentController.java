package com.study.board.controller;

import com.study.board.domain.LikeAndDislike;
import com.study.board.domain.board.BoardLikeDislike;
import com.study.board.domain.comment.Comment;
import com.study.board.domain.comment.CommentLikeDislike;
import com.study.board.domain.comment.CommentUpdate;
import com.study.board.domain.comment.CommentWrite;
import com.study.board.dto.board.*;
import com.study.board.dto.comment.CommentLikeDislikeDto;
import com.study.board.dto.comment.CommentUpdateDto;
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
    public List<BoardDto> getBoards(HttpServletRequest request) {
        int curPage = Integer.parseInt(request.getParameter("page"));
        Long perPage = 10L;

        SearchAndPagingDto searchAndPagingDto = SearchAndPagingDto.builder()
                .perPage(perPage)
                .offset(Long.valueOf((curPage - 1) * perPage))
                .build();
        return boardService.findBoards(searchAndPagingDto);
    }

    /**
     * 게시판 리스트 검색
     * @param searchKeyword
     * @param request
     * @return
     */
    @GetMapping("/boards/search/{searchKeyword}")
    public List<BoardDto> getBoardsWithSearch(@PathVariable("searchKeyword") String searchKeyword,
                                              HttpServletRequest request) {
        String searchType = request.getParameter("type");
        Long curPage = Long.valueOf(request.getParameter("page"));
        Long perPage = 10L;

        SearchAndPagingDto searchAndPagingDto = SearchAndPagingDto.builder()
                .searchType(searchType)
                .searchKeyword(searchKeyword)
                .perPage(perPage)
                .offset(Long.valueOf((curPage - 1) * perPage))
                .build();
        return boardService.findBoardsWithSearch(searchAndPagingDto);
    }

    /**
     * 상세페이지
     * @param board_idx
     * @return
     */
    @GetMapping("/board/{board_idx}")
    public BoardDto getBoardDetail(@PathVariable("board_idx") Long board_idx) {
        return boardService.findBoardDetail(board_idx);
    }

    /**
     * 게시글 작성
     * @param boardWriteDto
     * @param bindingResult
     */
    @PostMapping("/board")
    public void writeBoard(@Validated @RequestBody BoardWriteDto boardWriteDto, BindingResult bindingResult) {
        boardService.writeBoard(boardWriteDto);
    }

    /**
     * 게시글 삭제
     * @param board_idx
     */
    @DeleteMapping("/board/{board_idx}")
    public void deleteBoard(@PathVariable("board_idx") Long board_idx) {
        boardService.deleteBoard(board_idx);
    }

    /**
     * 게시글 수정
     * @param board_idx
     * @param boardUpdateDto
     * @param bindingResult
     */
    @PutMapping("/board/{board_idx}")
    public void updateBoard(@Validated @RequestBody BoardUpdateDto boardUpdateDto, BindingResult bindingResult,
                            @PathVariable("board_idx") Long board_idx) {
        boardUpdateDto.setBoard_idx(board_idx);
        boardService.updateBoard(boardUpdateDto);
    }

    /**
     * 게시글 좋아요
     * @param boardLikeDislikeDto
     * @param bindingResult
     */
    @PostMapping("/board/like")
    public void likeBoard(@Validated @RequestBody BoardLikeDislikeDto boardLikeDislikeDto, BindingResult bindingResult) {
        BoardLikeDislike boardLikeDislike = BoardLikeDislike.builder()
                .board_idx(boardLikeDislikeDto.getBoard_idx())
                .member_idx(boardLikeDislikeDto.getMember_idx())
                .board_like_dislike(String.valueOf(LikeAndDislike.LIKE.ordinal()))
                .build();
        boardService.likeAndDislikeBoard(boardLikeDislike);
    }

    /**
     * 게시글 싫어요
     * @param boardLikeDislikeDto
     * @param bindingResult
     */
    @PostMapping("/board/dislike")
    public void dislikeBoard(@Validated @RequestBody BoardLikeDislikeDto boardLikeDislikeDto, BindingResult bindingResult) {
        BoardLikeDislike boardLikeDislike = BoardLikeDislike.builder()
                .board_idx(boardLikeDislikeDto.getBoard_idx())
                .member_idx(boardLikeDislikeDto.getMember_idx())
                .board_like_dislike(String.valueOf(LikeAndDislike.DISLIKE.ordinal()))
                .build();
        boardService.likeAndDislikeBoard(boardLikeDislike);
    }

    /*Comment*/
    /**
     * 댓글 조회
     * @param board_idx
     * @return
     */
    @GetMapping("/board/{board_idx}/comment")
    public List<Comment> getComments(@Validated @PathVariable("board_idx") Long board_idx) {
        return commentService.findComments(board_idx);
    }

    /**
     * 댓글 작성
     * @param commentWriteDto
     * @param bindingResult
     * @param board_idx
     */
    @PostMapping("/board/{board_idx}/comment")
    public void writeComment(@Validated @RequestBody CommentWriteDto commentWriteDto, BindingResult bindingResult,
                             @PathVariable("board_idx") Long board_idx) {
        CommentWrite commentWrite = new CommentWrite();
        commentWrite.setBoard_idx(board_idx);
        commentWrite.setMember_idx(commentWriteDto.getMember_idx());
        commentWrite.setComment_content(commentWriteDto.getComment_content());
        System.out.println("commentWrite = " + commentWrite);
        commentService.writeComment(commentWrite);
    }

    /**
     * 댓글 삭제
     * @param comment_idx
     */
    @DeleteMapping("/comment/{comment_idx}")
    public void deleteComment(@PathVariable("comment_idx") Long comment_idx) {
        commentService.deleteComment(comment_idx);
    }

    /**
     * 댓글 수정
     * @param commentUpdateDto
     * @param bindingResult
     * @param comment_idx
     */
    @PutMapping("/comment/{comment_idx}")
    public void updateComment(@Validated @RequestBody CommentUpdateDto commentUpdateDto, BindingResult bindingResult,
                              @PathVariable("comment_idx") Long comment_idx) {
        CommentUpdate commentUpdate = new CommentUpdate();
        commentUpdate.setComment_idx(comment_idx);
        commentUpdate.setComment_content(commentUpdateDto.getComment_content());
        commentService.updateComment(commentUpdate);
    }

    /**
     * 댓글 좋아요
     * @param commentLikeDislikeDto
     */
    @PostMapping("/comment/like")
    public void likeComment(@Validated @RequestBody CommentLikeDislikeDto commentLikeDislikeDto) {
        CommentLikeDislike commentLikeDislike = new CommentLikeDislike();
        commentLikeDislike.setMember_idx(commentLikeDislikeDto.getMember_idx());
        commentLikeDislike.setComment_idx(commentLikeDislikeDto.getComment_idx());
        commentLikeDislike.setComment_like_dislike(String.valueOf(LikeAndDislike.LIKE.ordinal()));
        commentService.likeComment(commentLikeDislike);
    }

    /**
     * 댓글 싫어요
     * @param commentLikeDislikeDto
     */
    @PostMapping("/comment/dislike")
    public void dislikeComment(@Validated @RequestBody CommentLikeDislikeDto commentLikeDislikeDto) {
        CommentLikeDislike commentLikeDislike = new CommentLikeDislike();
        commentLikeDislike.setMember_idx(commentLikeDislikeDto.getMember_idx());
        commentLikeDislike.setComment_idx(commentLikeDislikeDto.getComment_idx());
        commentLikeDislike.setComment_like_dislike(String.valueOf(LikeAndDislike.DISLIKE.ordinal()));
        commentService.dislikeComment(commentLikeDislike);
    }
}
