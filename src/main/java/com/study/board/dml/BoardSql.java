package com.study.board.dml;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class BoardSql {

    public String findBoardList(@Param("offset") Integer offset, @Param("perPage") Integer perPage) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("board b")
                .LIMIT("#{offset},#{perPage}");
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    public String findBoardListBySearchKeyword(@Param("searchType") String searchType, @Param("searchKeyword") String searchKeyword, @Param("offset") Integer offset, @Param("perPage") Integer perPage) {
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("board b");
            JOIN("member m ON b.member_idx=m.member_idx");
            LIMIT("#{offset},#{perPage}");
        }};
        searchingQuery(searchType, searchKeyword, sql);
        log.info("sql={}", sql.toString());
        return sql.toString();
    }

    private void searchingQuery(String searchType, String searchKeyword, SQL sql) {
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


}
