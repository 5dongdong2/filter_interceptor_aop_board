package com.study.board.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BoardUpdate {

    private Long board_idx;

    private String board_title;

    private String board_content;
}
