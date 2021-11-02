package com.study.board.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchAndPaging {
    private String searchKeyword;
    private String searchType;
    private Integer perPage;
    private Integer offset;
}
