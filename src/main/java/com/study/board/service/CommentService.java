package com.study.board.service;

import com.study.board.domain.comment.Comment;
import com.study.board.domain.comment.CommentLikeDislike;
import com.study.board.domain.comment.CommentUpdate;
import com.study.board.domain.comment.CommentWrite;

import java.util.List;

public interface CommentService {

    public List<Comment> findComments(Long boardIdx);

    public void writeComment(CommentWrite commentWrite);

    public void deleteComment(Long commentIdx);

    public void updateComment(CommentUpdate commentUpdate);

    public void likeComment(CommentLikeDislike commentLikeDislike);

    public void dislikeComment(CommentLikeDislike commentLikeDislike);
}
