package com.study.board.mapper;

import com.study.board.dml.BoardSql;
import com.study.board.domain.Board;
import com.study.board.domain.WriteParameter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface BoardMapper {

    @SelectProvider(type = BoardSql.class, method = "findBoardList")
    public List<Board> findBoardList(@Param("offset") Integer offset,
                                     @Param("perPage") Integer perPage);

    @SelectProvider(type = BoardSql.class, method = "findBoardListBySearchKeyword")
    public List<Board> findBoardListWithSearch(@Param("searchType") String searchType,
                                     @Param("searchKeyword") String searchKeyword,
                                     @Param("offset") Integer offset,
                                     @Param("perPage") Integer perPage);

    @SelectProvider(type = BoardSql.class, method = "findBoardDetailByIdx")
    public Board findBoardDetailByIdx(@Param("board_idx") Long board_idx);

    @SelectProvider(type = BoardSql.class, method = "insert")
    public void writeBoard(@Param("writeSqlParameter") WriteParameter writeParameter);
}
