package com.study.board.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BoardWrite {

    private Long memberIdx;
    private String boardTitle;
    private String boardContent;
}
