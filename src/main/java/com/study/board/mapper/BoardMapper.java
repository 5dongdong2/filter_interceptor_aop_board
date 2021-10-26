package com.study.board.mapper;

import com.study.board.dml.BoardSql;
import com.study.board.domain.Board;
import com.study.board.domain.BoardSqlParameter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface BoardMapper {

    @SelectProvider(type = BoardSql.class, method = "findBoardBySearchKeyword")
    public List<Board> findBoardList(@Param("searchType") String searchType,
                                     @Param("searchKeyword") String searchKeyword,
                                     @Param("offset") Integer offset,
                                     @Param("perPage") Integer perPage);
//    public List<Board> findBoardList(@Param("boardSqlParameter.getSearchKeyword()") BoardSqlParameter boardSqlParameter);

}
