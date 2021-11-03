package com.study.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingAndSearchingSqlParameter {

    private String searchKeyword;
    private String searchType;
    private Integer perPage;
    private Integer offset;
}
