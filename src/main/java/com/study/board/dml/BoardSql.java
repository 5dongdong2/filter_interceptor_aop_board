package com.study.board.dml;

import com.study.board.domain.board.BoardLikeDislike;
import com.study.board.domain.board.BoardUpdate;
import com.study.board.domain.board.BoardWrite;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
@Mapper
public class BoardSql {

    public String findBoards(@Param("offset") Long offset, @Param("perPage") Long perPage) {
        SQL sql = new SQL()
                .SELECT("b.*, m.member_name")
                .FROM("board b")
                .JOIN("member m ON b.member_idx=m.member_idx")
                .LIMIT("#{offset},#{perPage}");
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    public String findBoardsWithSearch(@Param("searchType") String searchType,
                                       @Param("searchKeyword") String searchKeyword,
                                       @Param("offset") Long offset,
                                       @Param("perPage") Long perPage) {
        SQL sql = new SQL() {{
            SELECT("b.*, m.member_name");
            FROM("board b");
            JOIN("member m ON b.member_idx=m.member_idx");
            LIMIT("#{offset},#{perPage}");
        }};
        querySelectorBySearchType(searchType, searchKeyword, sql);
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    private void querySelectorBySearchType(String searchType, String searchKeyword, SQL sql) {
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

    public String findBoardDetail(@Param("boardIdx") Long boardIdx) {
        SQL sql = new SQL() {{
            SELECT("b.*, m.member_name");
            FROM("board b");
            JOIN("member m ON b.member_idx=m.member_idx");
            WHERE("b.board_idx=#{boardIdx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    public String insertBoard(@Param("boardWrite") BoardWrite boardWrite) {
        SQL sql = new SQL(){{
            INSERT_INTO("board");
            INTO_COLUMNS("member_idx", "board_title", "board_content", "board_create_date", "board_update_date");
            INTO_VALUES("#{boardWrite.memberIdx}", "#{boardWrite.boardTitle}", "#{boardWrite.boardContent}", "NOW()", "NOW()");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    public String deleteBoard(@Param("boardIdx") Long boardIdx) {
        SQL sql = new SQL() {{
            DELETE_FROM("board");
            WHERE("board_idx=#{boardIdx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    public String updateBoard(@Param("boardUpdate") BoardUpdate boardUpdate) {
        SQL sql = new SQL() {{
            UPDATE("board");
            SET("board_title=#{boardUpdate.boardTitle}, board_content=#{boardUpdate.boardContent}, board_update_date=NOW()");
            WHERE("board_idx=#{boardUpdate.boardIdx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    public String insertLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike) {
        SQL sql = new SQL() {{
            INSERT_INTO("board_like_dislike");
            INTO_COLUMNS("board_idx, member_idx, board_like_dislike");
            INTO_VALUES("#{boardLikeDislike.boardIdx}, #{boardLikeDislike.memberIdx}, #{boardLikeDislike.boardLikeDislike}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    public String deleteLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike) {
        SQL sql = new SQL() {{
            DELETE_FROM("board_like_dislike");
            WHERE("member_idx=#{boardLikeDislike.memberIdx} AND board_idx=#{boardLikeDislike.boardIdx} AND board_like_dislike=#{boardLikeDislike.boardLikeDislike}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    public String checkLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike) {
        SQL sql = new SQL() {{
            SELECT("COUNT(*) AS `count`");
            FROM("board_like_dislike");
            WHERE("member_idx=#{boardLikeDislike.memberIdx} AND board_idx=#{boardLikeDislike.boardIdx} AND board_like_dislike=#{boardLikeDislike.boardLikeDislike}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    public String updateBoardLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike) {
        SQL sql = new SQL() {{
            UPDATE("board");
            if (boardLikeDislike.getBoardLikeDislike().equals("0")) {
                SET("board_dislike=(SELECT COUNT(*) FROM board_like_dislike WHERE board_idx=#{boardLikeDislike.boardIdx} AND board_like_dislike='0')");
            }
            if (boardLikeDislike.getBoardLikeDislike().equals("1")) {
                SET("board_like=(SELECT COUNT(*) FROM board_like_dislike WHERE board_idx=#{boardLikeDislike.boardIdx} AND board_like_dislike='1')");
            }
            WHERE("board_idx=#{boardLikeDislike.boardIdx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }
}
