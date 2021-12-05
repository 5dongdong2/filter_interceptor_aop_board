package com.study.board.mapper;

import com.study.board.dml.BoardSql;
import com.study.board.dto.board.BoardDto;
import com.study.board.domain.board.BoardLikeDislike;
import com.study.board.domain.board.BoardUpdate;
import com.study.board.domain.board.BoardWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface BoardMapper {

    @SelectProvider(type = BoardSql.class, method = "findBoards")
    public List<BoardDto> findBoards(@Param("offset") Long offset,
                                     @Param("perPage") Long perPage);

    @SelectProvider(type = BoardSql.class, method = "findBoardsWithSearch")
    public List<BoardDto> findBoardsWithSearch(@Param("searchType") String searchType,
                                               @Param("searchKeyword") String searchKeyword,
                                               @Param("offset") Long offset,
                                               @Param("perPage") Long perPage);

    @SelectProvider(type = BoardSql.class, method = "findBoardDetail")
    public BoardDto findBoardDetail(@Param("boardIdx") Long boardIdx);

    @SelectProvider(type = BoardSql.class, method = "insertBoard")
    public void insertBoard(@Param("boardWrite") BoardWrite boardWrite);

    @SelectProvider(type = BoardSql.class, method = "deleteBoard")
    public void deleteBoard(@Param("boardIdx") Long boardIdx);

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
