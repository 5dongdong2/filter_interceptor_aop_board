package com.study.board.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class PageAndSearchDto {
    private String searchKeyword;
    private String searchType;
    private Long perPage;
    private Long offset;
}
