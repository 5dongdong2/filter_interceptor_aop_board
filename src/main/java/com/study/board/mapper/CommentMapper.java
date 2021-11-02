package com.study.board.mapper;

import com.study.board.dml.CommentSql;
import com.study.board.domain.comment.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface CommentMapper {

    @SelectProvider(type = CommentSql.class, method = "findComments")
    public List<Comment> findComments(@Param("board_idx") Long board_idx);

}
