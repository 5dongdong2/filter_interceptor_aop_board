package com.study.board.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BoardUpdate {

    private Long boardIdx;

    private String boardTitle;

    private String boardContent;
}
