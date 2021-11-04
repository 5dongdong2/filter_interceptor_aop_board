package com.study.board.service;

import com.study.board.domain.comment.Comment;
import com.study.board.domain.comment.CommentLikeDislike;
import com.study.board.domain.comment.CommentUpdate;
import com.study.board.domain.comment.CommentWrite;

import java.util.List;

public interface CommentService {

    /**
     * 댓글 조회
     * @param boardIdx
     * @return
     */
    public List<Comment> findComments(Long boardIdx);

    /**
     * 댓글 작성
     * @param commentWrite
     */
    public void writeComment(CommentWrite commentWrite);

    /**
     * 댓글 삭제
     * @param commentIdx
     */
    public void deleteComment(Long commentIdx);

    /**
     * 댓글 수정
     * @param commentUpdate
     */
    public void updateComment(CommentUpdate commentUpdate);

    /**
     * 댓글 좋아요
     * @param commentLikeDislike
     */
    public void likeComment(CommentLikeDislike commentLikeDislike);

    /**
     * 댓글 싫어요
     * @param commentLikeDislike
     */
    public void dislikeComment(CommentLikeDislike commentLikeDislike);

}
