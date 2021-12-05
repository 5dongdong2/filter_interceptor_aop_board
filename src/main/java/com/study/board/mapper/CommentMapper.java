package com.study.board.mapper;

import com.study.board.dml.CommentSql;
import com.study.board.domain.comment.Comment;
import com.study.board.domain.comment.CommentLikeDislike;
import com.study.board.domain.comment.CommentUpdate;
import com.study.board.domain.comment.CommentWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface CommentMapper {

    @SelectProvider(type = CommentSql.class, method = "insertComment")
    public void insertComment(@Param("commentWrite") CommentWrite commentWrite);

    @SelectProvider(type = CommentSql.class, method = "findComments")
    public List<Comment> findComments(@Param("boardIdx") Long boardIdx);

    @SelectProvider(type = CommentSql.class, method = "deleteComment")
    public void deleteComment(@Param("commentIdx") Long commentIdx);

    @SelectProvider(type = CommentSql.class, method = "updateComment")
    public void updateComment(@Param("commentUpdate") CommentUpdate commentUpdate);

    @SelectProvider(type = CommentSql.class, method = "insertLikeAndDislike")
    public void insertLikeAndDislike(@Param("commentLikeDislike") CommentLikeDislike commentLikeDislike);

    @SelectProvider(type = CommentSql.class, method = "deleteLikeAndDislike")
    public void deleteLikeAndDislike(@Param("commentLikeDislike") CommentLikeDislike commentLikeDislike);

    @SelectProvider(type = CommentSql.class, method = "checkLikeDislike")
    public int checkLikeDislike(@Param("commentLikeDislike") CommentLikeDislike commentLikeDislike);

    @SelectProvider(type = CommentSql.class, method = "updateCommentLike")
    public void updateCommentLike(@Param("commentIdx") Long commentIdx);

    @SelectProvider(type = CommentSql.class, method = "updateCommentDislike")
    public void updateCommentDislike(@Param("commentIdx") Long commentIdx);
}
