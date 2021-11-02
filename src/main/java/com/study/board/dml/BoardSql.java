package com.study.board.dml;

import com.study.board.domain.board.BoardLikeDislike;
import com.study.board.domain.board.BoardUpdate;
import com.study.board.dto.board.BoardWriteDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class BoardSql {

    /**
     * 리스트(paging)
     * @param offset
     * @param perPage
     * @return
     */
    public String findBoardList(@Param("offset") Integer offset, @Param("perPage") Integer perPage) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("board b")
                .LIMIT("#{offset},#{perPage}");
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 리스트(paging + search)
     * @param searchType
     * @param searchKeyword
     * @param offset
     * @param perPage
     * @return
     */
    public String findBoardListBySearchKeyword(@Param("searchType") String searchType, @Param("searchKeyword") String searchKeyword, @Param("offset") Integer offset, @Param("perPage") Integer perPage) {
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("board b");
            JOIN("member m ON b.member_idx=m.member_idx");
            LIMIT("#{offset},#{perPage}");
        }};
        queryBySearchType(searchType, searchKeyword, sql);
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 검색 type에 따른 query 설정
     * @param searchType
     * @param searchKeyword
     * @param sql
     */
    private void queryBySearchType(String searchType, String searchKeyword, SQL sql) {
        String[] type = {"b.board_title", "b.board_content", "m.member_name"};
        if (searchType.equals("title")) {
            sql.WHERE(type[0] + " LIKE CONCAT('%',#{searchKeyword},'%')");

        } else if (searchType.equals("content")) {
            sql.WHERE(type[1] + " LIKE CONCAT('%',#{searchKeyword},'%')");

        } else if (searchType.equals("name")) {
            sql.WHERE(type[2] + " LIKE CONCAT('%',#{searchKeyword},'%')");

        } else if (searchType.equals("total")) {
            for (int i = 0; i < type.length; i++) {
                sql.WHERE(type[i] + " LIKE CONCAT('%',#{searchKeyword},'%')");
                if (i < type.length - 1) {
                    sql.OR();
                }
            }
        }
    }

    /**
     * 상세페이지
     * @param board_idx
     * @return
     */
    public String findBoardDetailByIdx(@Param("board_idx") Long board_idx) {
        SQL sql = new SQL() {{
            SELECT("b.*, m.member_name");
            FROM("board b");
            JOIN("member m ON b.member_idx=m.member_idx");
            WHERE("b.board_idx=#{board_idx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 게시글 작성
     * @param boardWriteDto
     * @return
     */
    public String insertBoard(@Param("boardWrite") BoardWriteDto boardWriteDto) {
        SQL sql = new SQL(){{
            INSERT_INTO("board");
            INTO_COLUMNS("member_idx", "board_title", "board_content", "board_create_date", "board_update_date");
            INTO_VALUES("#{boardWrite.member_idx}", "#{boardWrite.board_title}", "#{boardWrite.board_content}", "NOW()", "NOW()");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 게시글 삭제
     * @param board_idx
     * @return
     */
    public String deleteBoard(@Param("board_idx") Long board_idx) {
        SQL sql = new SQL() {{
            DELETE_FROM("board");
            WHERE("board_idx=#{board_idx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 게시글 수정
     * @param boardUpdate
     * @return
     */
    public String updateBoard(@Param("boardUpdate") BoardUpdate boardUpdate) {
        SQL sql = new SQL() {{
            UPDATE("board");
            SET("board_title=#{boardUpdate.board_title}, board_content=#{boardUpdate.board_content}, board_update_date=NOW()");
            WHERE("board_idx=#{boardUpdate.board_idx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 좋아요 및 싫어요
     * @param boardLikeDislike
     * @return
     */
    public String insertLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(boardLikeDislike.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        SQL sql = new SQL() {{
            INSERT_INTO("board_like_dislike");
            INTO_COLUMNS("board_idx, member_idx, board_like_dislike");
            INTO_VALUES("#{boardLikeDislike.board_idx}, #{boardLikeDislike.member_idx}, #{boardLikeDislike.board_like_dislike}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 좋아요 및 싫어요 취소
     * @param boardLikeDislike
     * @return
     */
    public String deleteLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike) {
        SQL sql = new SQL() {{
            DELETE_FROM("board_like_dislike");
            WHERE("member_idx=#{boardLikeDislike.member_idx} AND board_idx=#{boardLikeDislike.board_idx} AND board_like_dislike=#{boardLikeDislike.board_like_dislike}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 좋아요 및 싫어요 이력확인
     * @param boardLikeDislike
     * @return
     */
    public String checkLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike) {
        SQL sql = new SQL() {{
            SELECT("COUNT(*) AS `count`");
            FROM("board_like_dislike");
            WHERE("member_idx=#{boardLikeDislike.member_idx} AND board_idx=#{boardLikeDislike.board_idx} AND board_like_dislike=#{boardLikeDislike.board_like_dislike}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /**
     * 좋아요 및 싫어요 수 update
     * @param boardLikeDislike
     * @return
     */
    public String updateBoardLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike) {
        SQL sql = new SQL() {{
            UPDATE("board");
            if (boardLikeDislike.getBoard_like_dislike().equals("0")) {
                SET("board_dislike=(SELECT COUNT(*) FROM board_like_dislike WHERE board_idx=#{boardLikeDislike.board_idx} AND board_like_dislike='0')");
            }
            if (boardLikeDislike.getBoard_like_dislike().equals("1")) {
                SET("board_like=(SELECT COUNT(*) FROM board_like_dislike WHERE board_idx=#{boardLikeDislike.board_idx} AND board_like_dislike='1')");
            }
            WHERE("board_idx=#{boardLikeDislike.board_idx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }
}
