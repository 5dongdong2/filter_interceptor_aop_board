package com.study.board.service;

import com.study.board.dml.CommentSql;
import com.study.board.domain.comment.Comment;
import com.study.board.domain.comment.CommentLikeDislike;
import com.study.board.domain.comment.CommentUpdate;
import com.study.board.domain.comment.CommentWrite;
import com.study.board.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> findComments(Long board_idx) {
        return commentMapper.findComments(board_idx);
    }

    @Override
    public void writeComment(CommentWrite commentWrite) {

    }

    @Override
    public void deleteComment(Long comment_idx) {

    }

    @Override
    public void updateComment(CommentUpdate commentUpdate) {

    }

    @Override
    public void likeComment(CommentLikeDislike commentLikeDislike) {

    }

    @Override
    public void dislikeComment(CommentLikeDislike commentLikeDislike) {

    }
}
