package com.study.board.dml;

import com.study.board.domain.BoardSqlParameter;
import org.springframework.util.StringUtils;

public class BoardSql {

    public String findBoardBySearchKeyword(BoardSqlParameter boardSqlParameter) {
        //1번 방법 - StringBuilder 사용하기
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM board b ");
        //검색
        searchingQuery(boardSqlParameter, query);
        //페이징
        pagingQuery(boardSqlParameter, query);
        return query.toString();
    }

    private void pagingQuery(BoardSqlParameter boardSqlParameter, StringBuilder query) {
        query.append(" LIMIT ");
        query.append(boardSqlParameter.getOffset());
        query.append(",");
        query.append(boardSqlParameter.getPerPage());
    }

    private void searchingQuery(BoardSqlParameter boardSqlParameter, StringBuilder query) {
        String[] type = {"b.board_title", "b.board_content", "m.member_name"};
        if (boardSqlParameter.getSearchType().equals("title")) {
            query.append(" WHERE ");
            query.append(type[0]);
            query.append(" LIKE CONCAT('%',#{");
            query.append(boardSqlParameter.getSearchKeyword());
            query.append("} ");

        } else if (boardSqlParameter.getSearchType().equals("content")) {
            query.append(" WHERE ");
            query.append(type[1]);
            query.append(" LIKE CONCAT('%',#{");
            query.append(boardSqlParameter.getSearchKeyword());
            query.append("} ");

        } else if (boardSqlParameter.getSearchType().equals("name")) {
            query.append(" WHERE ");
            query.append(type[2]);
            query.append(" LIKE CONCAT('%',#{");
            query.append(boardSqlParameter.getSearchKeyword());
            query.append("} ");

        } else if (boardSqlParameter.getSearchType().equals("total")) {
            query.append(" WHERE ");
            for (int i = 0; i < type.length; i++) {
                if (i != 0) {
                    query.append(" OR ");
                }
                query.append(type[i]);
                query.append(" LIKE CONCAT('%',#{");
                query.append(boardSqlParameter.getSearchKeyword());
                query.append("} ");
            }
        }
    }
}
