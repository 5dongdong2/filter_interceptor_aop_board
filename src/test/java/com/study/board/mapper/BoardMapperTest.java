package com.study.board.mapper;

import com.study.board.domain.board.BoardWrite;
import com.study.board.dto.board.BoardDto;
import com.study.board.dto.board.SearchAndPagingDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Transactional(readOnly = true)
@SpringBootTest
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @MockBean
    private BoardMapper mockBoardMapper;

    @Test
    @DisplayName("게시판 목록")
    void getBoards() {
        //given
        Long offset = 0L;
        Long perPage = 10L;

        //when
        List<BoardDto> boards = boardMapper.findBoards(offset, perPage);

        //then
        assertThat(0).isSameAs(boards.size());
    }

    @Test
    @DisplayName("검색 시 게시판 목록")
    void getBoardsWithSearch() {
        //given
        BoardWrite boardWrite = mock(BoardWrite.class);
        mockBoardMapper.insertBoard(boardWrite);

        Long offset = 0L;
        Long perPage = 10L;
        String searchType = "total";
        String searchKeyword = "";
        SearchAndPagingDto searchAndPagingDto = SearchAndPagingDto.builder()
                .offset(offset)
                .perPage(perPage)
                .searchType(searchType)
                .searchKeyword(searchKeyword)
                .build();

        //when
//        boardMapper.findBoardsWithSearch(searchType, searchKeyword, offset, perPage);

        //then
//        assertThat().isSameAs(boardDto)
    }
}