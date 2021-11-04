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

    /**
     * 게시글 리스트
     * @param offset
     * @param perPage
     * @return
     */
    @SelectProvider(type = BoardSql.class, method = "findBoards")
    public List<BoardDto> findBoards(@Param("offset") Long offset,
                                     @Param("perPage") Long perPage);

    /**
     * 게시글 리스트(검색)
     * @param searchType
     * @param searchKeyword
     * @param offset
     * @param perPage
     * @return
     */
    @SelectProvider(type = BoardSql.class, method = "findBoardsWithSearch")
    public List<BoardDto> findBoardsWithSearch(@Param("searchType") String searchType,
                                               @Param("searchKeyword") String searchKeyword,
                                               @Param("offset") Long offset,
                                               @Param("perPage") Long perPage);

    /**
     * 상세페이지
     * @param board_idx
     * @return
     */
    @SelectProvider(type = BoardSql.class, method = "findBoardDetail")
    public BoardDto findBoardDetail(@Param("board_idx") Long board_idx);

    /**
     * 게시글 작성
     * @param boardWrite
     */
    @SelectProvider(type = BoardSql.class, method = "insertBoard")
    public void insertBoard(@Param("boardWrite") BoardWrite boardWrite);

    /**
     * 게시글 삭제
     * @param board_idx
     */
    @SelectProvider(type = BoardSql.class, method = "deleteBoard")
    public void deleteBoard(@Param("board_idx") Long board_idx);

    /**
     * 게시글 수정
     * @param boardUpdate
     */
    @SelectProvider(type = BoardSql.class, method = "updateBoard")
    public void updateBoard(@Param("boardUpdate") BoardUpdate boardUpdate);

    /**
     * 좋아요 및 싫어요
     * @param boardLikeDislike
     */
    @SelectProvider(type = BoardSql.class, method = "insertLikeDislike")
    public void insertLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike);

    /**
     * 좋아요 및 싫어요 취소
     * @param boardLikeDislike
     */
    @SelectProvider(type = BoardSql.class, method = "deleteLikeDislike")
    public void deleteLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike);

    /**
     * 좋아요 및 싫어요 이력확인
     * @param boardLikeDislike
     * @return
     */
    @SelectProvider(type = BoardSql.class, method = "checkLikeDislike")
    public Integer checkLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike);

    /**
     * 좋아요 및 싫어요 수 update
     * @param boardLikeDislike
     */
    @SelectProvider(type = BoardSql.class, method = "updateBoardLikeDislike")
    public void updateBoardLikeDislike(@Param("boardLikeDislike") BoardLikeDislike boardLikeDislike);
}
