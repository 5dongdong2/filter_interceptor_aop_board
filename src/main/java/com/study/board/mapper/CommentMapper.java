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

    /**
     * 댓글 작성
     * @param commentWrite
     */
    @SelectProvider(type = CommentSql.class, method = "insertComment")
    public void insertComment(@Param("commentWrite") CommentWrite commentWrite);

    /**
     * 댓글 조회
     * @param boardIdx
     * @return
     */
    @SelectProvider(type = CommentSql.class, method = "findComments")
    public List<Comment> findComments(@Param("boardIdx") Long boardIdx);

    /**
     * 댓글 삭제
     * @param commentIdx
     */
    @SelectProvider(type = CommentSql.class, method = "deleteComment")
    public void deleteComment(@Param("commentIdx") Long commentIdx);

    /**
     * 댓글 수정
     * @param commentUpdate
     */
    @SelectProvider(type = CommentSql.class, method = "updateComment")
    public void updateComment(@Param("commentUpdate") CommentUpdate commentUpdate);

    /**
     * 댓글 좋아요 및 싫어요
     * @param commentLikeDislike
     */
    @SelectProvider(type = CommentSql.class, method = "insertLikeAndDislike")
    public void insertLikeAndDislike(@Param("commentLikeDislike") CommentLikeDislike commentLikeDislike);

    /**
     * 댓글 좋아요 및 싫어요 취소
     * @param commentLikeDislike
     */
    @SelectProvider(type = CommentSql.class, method = "deleteLikeAndDislike")
    public void deleteLikeAndDislike(@Param("commentLikeDislike") CommentLikeDislike commentLikeDislike);

    /**
     * 댓글 좋아요 및 싫어요 이력확인
     * @param commentLikeDislike
     * @return
     */
    @SelectProvider(type = CommentSql.class, method = "checkLikeDislike")
    public int checkLikeDislike(@Param("commentLikeDislike") CommentLikeDislike commentLikeDislike);

    /**
     * 좋아요 수 update
     * @param commentIdx
     */
    @SelectProvider(type = CommentSql.class, method = "updateCommentLike")
    public void updateCommentLike(@Param("commentIdx") Long commentIdx);

    /**
     * 싫어요 수 update
     * @param commentIdx
     */
    @SelectProvider(type = CommentSql.class, method = "updateCommentDislike")
    public void updateCommentDislike(@Param("commentIdx") Long commentIdx);
}
