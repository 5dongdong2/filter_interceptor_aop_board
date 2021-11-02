package com.study.board.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardUpdate {

    private Long board_idx;

    private String board_title;

    private String board_content;
}
