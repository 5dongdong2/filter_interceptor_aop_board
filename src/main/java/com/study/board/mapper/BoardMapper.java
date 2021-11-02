package com.study.board.mapper;

import com.study.board.dml.BoardSql;
import com.study.board.domain.board.Board;
import com.study.board.domain.board.BoardLikeDislike;
import com.study.board.domain.board.BoardUpdate;
import com.study.board.domain.board.BoardWrite;
import com.study.board.dto.board.BoardWriteDto;
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

    @SelectProvider(type = BoardSql.class, method = "insertBoard")
    public void writeBoard(@Param("boardWrite") BoardWrite boardWrite);

    @SelectProvider(type = BoardSql.class, method = "deleteBoard")
    public void deleteBoard(@Param("board_idx") Long board_idx);

    @SelectProvider(type = BoardSql.class, method = "updateBoard")
    public void updateBoard(@Param("boardUpdate") BoardUpdate boardUpdate);

    @SelectProvider(type = BoardSql.class, method = "insertLikeDislike")
    public void insertLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike);

    @SelectProvider(type = BoardSql.class, method = "deleteLikeDislike")
    public void deleteLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike);

    @SelectProvider(type = BoardSql.class, method = "checkLikeDislike")
    public Integer checkLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike);

    @SelectProvider(type = BoardSql.class, method = "updateBoardLikeDislike")
    public void updateBoardLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike);

}
