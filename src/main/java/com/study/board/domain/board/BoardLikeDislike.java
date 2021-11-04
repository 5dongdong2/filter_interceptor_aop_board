package com.study.board.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BoardLikeDislike {

    private Long boardIdx;

    private Long memberIdx;

    private String boardLikeDislike;
}
