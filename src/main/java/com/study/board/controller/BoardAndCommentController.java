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
     * @param boardIdx
     * @return
     */
    @GetMapping("/board/{boardIdx}")
    public BoardDto getBoardDetail(@PathVariable("boardIdx") Long boardIdx) {
        return boardService.findBoardDetail(boardIdx);
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
     * @param boardIdx
     */
    @DeleteMapping("/board/{boardIdx}")
    public void deleteBoard(@PathVariable("boardIdx") Long boardIdx) {
        boardService.deleteBoard(boardIdx);
    }

    /**
     * 게시글 수정
     * @param boardIdx
     * @param boardUpdateDto
     * @param bindingResult
     */
    @PutMapping("/board/{boardIdx}")
    public void updateBoard(@Validated @RequestBody BoardUpdateDto boardUpdateDto, BindingResult bindingResult,
                            @PathVariable("boardIdx") Long boardIdx) {
        boardUpdateDto.setBoardIdx(boardIdx);
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
                .boardIdx(boardLikeDislikeDto.getBoardIdx())
                .memberIdx(boardLikeDislikeDto.getMemberIdx())
                .boardLikeDislike(String.valueOf(LikeAndDislike.LIKE.ordinal()))
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
                .boardIdx(boardLikeDislikeDto.getBoardIdx())
                .memberIdx(boardLikeDislikeDto.getMemberIdx())
                .boardLikeDislike(String.valueOf(LikeAndDislike.DISLIKE.ordinal()))
                .build();
        boardService.likeAndDislikeBoard(boardLikeDislike);
    }

    /*Comment*/
    /**
     * 댓글 조회
     * @param boardIdx
     * @return
     */
    @GetMapping("/board/{boardIdx}/comment")
    public List<Comment> getComments(@Validated @PathVariable("boardIdx") Long boardIdx) {
        return commentService.findComments(boardIdx);
    }

    /**
     * 댓글 작성
     * @param commentWriteDto
     * @param bindingResult
     * @param boardIdx
     */
    @PostMapping("/board/{boardIdx}/comment")
    public void writeComment(@Validated @RequestBody CommentWriteDto commentWriteDto, BindingResult bindingResult,
                             @PathVariable("boardIdx") Long boardIdx) {
        CommentWrite commentWrite = new CommentWrite();
        commentWrite.setBoardIdx(boardIdx);
        commentWrite.setMemberIdx(commentWriteDto.getMemberIdx());
        commentWrite.setCommentContent(commentWriteDto.getCommentContent());
        System.out.println("commentWrite = " + commentWrite);
        commentService.writeComment(commentWrite);
    }

    /**
     * 댓글 삭제
     * @param commentIdx
     */
    @DeleteMapping("/comment/{commentIdx}")
    public void deleteComment(@PathVariable("commentIdx") Long commentIdx) {
        commentService.deleteComment(commentIdx);
    }

    /**
     * 댓글 수정
     * @param commentUpdateDto
     * @param bindingResult
     * @param commentIdx
     */
    @PutMapping("/comment/{commentIdx}")
    public void updateComment(@Validated @RequestBody CommentUpdateDto commentUpdateDto, BindingResult bindingResult,
                              @PathVariable("commentIdx") Long commentIdx) {
        CommentUpdate commentUpdate = new CommentUpdate();
        commentUpdate.setCommentIdx(commentIdx);
        commentUpdate.setCommentContent(commentUpdateDto.getCommentContent());
        commentService.updateComment(commentUpdate);
    }

    /**
     * 댓글 좋아요
     * @param commentLikeDislikeDto
     */
    @PostMapping("/comment/like")
    public void likeComment(@Validated @RequestBody CommentLikeDislikeDto commentLikeDislikeDto) {
        CommentLikeDislike commentLikeDislike = new CommentLikeDislike();
        commentLikeDislike.setMemberIdx(commentLikeDislikeDto.getMemberIdx());
        commentLikeDislike.setCommentIdx(commentLikeDislikeDto.getCommentIdx());
        commentLikeDislike.setCommentLikeDislike(String.valueOf(LikeAndDislike.LIKE.ordinal()));
        commentService.likeComment(commentLikeDislike);
    }

    /**
     * 댓글 싫어요
     * @param commentLikeDislikeDto
     */
    @PostMapping("/comment/dislike")
    public void dislikeComment(@Validated @RequestBody CommentLikeDislikeDto commentLikeDislikeDto) {
        CommentLikeDislike commentLikeDislike = new CommentLikeDislike();
        commentLikeDislike.setMemberIdx(commentLikeDislikeDto.getMemberIdx());
        commentLikeDislike.setCommentIdx(commentLikeDislikeDto.getCommentIdx());
        commentLikeDislike.setCommentLikeDislike(String.valueOf(LikeAndDislike.DISLIKE.ordinal()));
        commentService.dislikeComment(commentLikeDislike);
    }
}
