package com.study.board.dml;

import com.study.board.domain.WriteParameter;
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
     * 글 작성
     * @param writeParameter
     * @return
     */
    public String insert(@Param("writeSqlParameter") WriteParameter writeParameter) {
        SQL sql = new SQL(){{
            INSERT_INTO("board");
            INTO_COLUMNS("member_idx", "board_title", "board_content", "board_create_date", "board_update_date");
            INTO_VALUES("#{writeSqlParameter.member_idx}", "#{writeSqlParameter.board_title}", "#{writeSqlParameter.board_content}", "NOW()", "NOW()");
        }};
        log.info("sql={}", sql.toString());
        return sql.toString();
    }
}
