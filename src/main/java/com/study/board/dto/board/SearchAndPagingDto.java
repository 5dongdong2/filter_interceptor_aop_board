package com.study.board.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SearchAndPagingDto {
    private String searchKeyword;
    private String searchType;
    private Long perPage;
    private Long offset;
}
