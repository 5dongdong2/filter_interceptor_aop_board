package com.study.board.dml;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class CommentSql {

    public String findComments(@Param("board_idx") Long board_idx) {
        SQL sql = new SQL() {{
            SELECT("c.*, m.member_name");
            FROM("comment c");
            JOIN("member m ON c.member_idx=m.member_idx");
            WHERE("c.board_idx=#{board_idx}");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    /*
        SQL sql = new SQL() {{

        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
     */
}
