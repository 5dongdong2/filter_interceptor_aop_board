package com.study.board.dml;

import com.study.board.domain.comment.CommentLikeDislike;
import com.study.board.domain.comment.CommentUpdate;
import com.study.board.domain.comment.CommentWrite;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class CommentSql {

    /**
     * 댓글 작성
     * @param commentWrite
     * @return
     */
    public String insertComment(@Param("commentWrite") CommentWrite commentWrite) {
        SQL sql = new SQL() {{
            INSERT_INTO("comment");
            INTO_COLUMNS("board_idx, member_idx, comment_content, comment_create_date, comment_update_date");
            INTO_VALUES("#{commentWrite.boardIdx},#{commentWrite.memberIdx},#{commentWrite.commentContent},NOW(),NOW()");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 댓글 조회
     * @param boardIdx
     * @return
     */
    public String findComments(@Param("boardIdx") Long boardIdx) {
        SQL sql = new SQL() {{
            SELECT("c.*, m.member_name");
            FROM("comment c");
            JOIN("member m ON c.member_idx=m.member_idx");
            WHERE("c.board_idx=#{boardIdx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 댓글 삭제
     * @param commentIdx
     * @return
     */
    public String deleteComment(@Param("commentIdx") Long commentIdx) {
        SQL sql = new SQL() {{
            DELETE_FROM("comment");
            WHERE("comment_idx=#{commentIdx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 댓글 수정
     * @return
     */
    public String updateComment(@Param("commentUpdate") CommentUpdate commentUpdate) {
        SQL sql = new SQL() {{
            UPDATE("comment");
            SET("comment_content=#{commentUpdate.commentContent}, comment_update_date=NOW()");
            WHERE("comment_idx=#{commentUpdate.commentIdx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 댓글 좋아요 및 싫어요
     * @return
     */
    public String insertLikeAndDislike(@Param("commentLikeDislike") CommentLikeDislike commentLikeDislike) {
        SQL sql = new SQL() {{
            INSERT_INTO("comment_like_dislike");
            INTO_COLUMNS("comment_idx, member_idx, comment_like_dislike");
            INTO_VALUES("#{commentLikeDislike.commentIdx},#{commentLikeDislike.memberIdx},#{commentLikeDislike.commentLikeDislike}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 댓글 좋아요 및 싫어요 취소
     * @param commentLikeDislike
     * @return
     */
    public String deleteLikeAndDislike(@Param("commentLikeDislike") CommentLikeDislike commentLikeDislike) {
        SQL sql = new SQL() {{
            DELETE_FROM("comment_like_dislike");
            WHERE("member_idx=#{commentLikeDislike.memberIdx} AND comment_idx=#{commentLikeDislike.commentIdx} AND comment_like_dislike=#{commentLikeDislike.commentLikeDislike}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 댓글 좋아요 및 싫어요 이력확인
     * @param commentLikeDislike
     * @return
     */
    public String checkLikeDislike(@Param("commentLikeDislike") CommentLikeDislike commentLikeDislike) {
        SQL sql = new SQL() {{
            SELECT("COUNT(*)");
            FROM("comment_like_dislike");
            WHERE("member_idx=#{commentLikeDislike.memberIdx} AND comment_idx=#{commentLikeDislike.commentIdx} AND comment_like_dislike=#{commentLikeDislike.commentLikeDislike}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 좋아요 수 update
     * @param commentIdx
     * @return
     */
    public String updateCommentLike(@Param("commentIdx") Long commentIdx) {
        SQL sql = new SQL() {{
            UPDATE("comment");
            SET("comment_like=(SELECT COUNT(*) FROM comment_like_dislike WHERE comment_idx=#{commentIdx} AND comment_like_dislike='1')");
            WHERE("comment_idx=#{commentIdx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 싫어요 수 update
     * @param commentIdx
     * @return
     */
    public String updateCommentDislike(@Param("commentIdx") Long commentIdx) {
        SQL sql = new SQL() {{
            UPDATE("comment");
            SET("comment_like=(SELECT COUNT(*) FROM comment_like_dislike WHERE comment_idx=#{commentIdx} AND comment_like_dislike='0')");
            WHERE("comment_idx=#{commentIdx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }
}
